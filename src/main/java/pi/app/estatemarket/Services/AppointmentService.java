package pi.app.estatemarket.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;

import pi.app.estatemarket.Entities.Appointment;
import pi.app.estatemarket.Entities.User;
import pi.app.estatemarket.Repository.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
//@Slf4j
@AllArgsConstructor
public class AppointmentService implements IAppointmentService {

    // @Autowired
    AppointmentRepository appointmentRepository;
    UserRepository userRepository ;
    AnnouncementRepository announcementRepository;

    @Override
    public Appointment addAppointment(Appointment appointment) throws Exception {
        // Vérifier si la date de rendez-vous est bien renseignée
        if (appointment.getDate() == null) {
            throw new Exception("La date de rendez-vous est obligatoire.");
        }

        // Vérifier si la date de rendez-vous est dans le futur
        if (appointment.getDate().before(new Date())) {
            throw new Exception("La date de rendez-vous doit être dans le futur.");
        }
       
        else {
            //sendsms("votre rendez vous est passé avec succées le "+appointment.getDate());
            return appointmentRepository.save(appointment);

        }
        
        
    }
	public void sendsms(String str) {
		Twilio.init("AC21a628a07a8990db4db08f1a67124b63", "3f17a1f426271c8d3c7879dde177530a");
		try {
			com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message
					.creator(new PhoneNumber(str), // To number
							new PhoneNumber("+15674093706"), // From number
							"votre réclamation a été traité")
					.create();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
    @Override
    public List<Appointment> getAppointment(){

        return appointmentRepository.findAll();
    }

    @Override
    public void deleteAppointment(Long id){

        appointmentRepository.deleteById(id);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment,Long id) throws Exception {
        // Récupérer l'entité de rendez-vous existante
        Appointment a=appointmentRepository.findById(id)
                .orElseThrow(() -> new Exception("Rendez-vous introuvable."));

        // Vérifier si la date de rendez-vous est bien renseignée
        if (appointment.getDate() == null) {
            throw new Exception("La date de rendez-vous est obligatoire.");
        }

        // Vérifier si la date de rendez-vous est dans le futur
        if (appointment.getDate().before(new Date())) {
            throw new Exception("La date de rendez-vous doit être dans le futur.");
        }

        // Mettre à jour l'entité existante avec les nouvelles valeurs
        a.setDate(appointment.getDate());
        a.setAppointmentStatus(appointment.isAppointmentStatus());
        a.setAnnouncementApp(appointment.getAnnouncementApp());
        a.setUsers(appointment.getUsers());

        //     User u=shiftservice.findUserWithoutShiftOnDate(a.getAppointmentDate());
        a.getUsers().clear();
        //     a.getUsers().add(u);
        a.setAnnouncementApp(a.getAnnouncementApp());
        // Enregistrer l'entité mise à jour dans la base de données
        return appointmentRepository.save(a);




    }

    @Override
    public void affecterUserAppointment(Long IdAppointment, Long userID) {
        User user= userRepository.findById(userID).orElse(null);
        Appointment appointment = appointmentRepository.findById(IdAppointment).orElse(null);
        //  user.getAppointments().add(appointment);
        appointment.getUsers().add(user);

        appointmentRepository.save(appointment);
    }

  /* @Override
    public List<Appointment> findAppointmentsByUsersAndDate(User user1, User user2, Date start, Date end) {
        return appointmentRepository.findAppointmentsByUsersAndDate(user1, user2, start, end);
    }*/

    @Override
    public List<Date> getAvailableDates(Long userId) {
        List<Appointment> userAppointments = appointmentRepository.findByUsers_userID(userId);
        List<Date> allDates = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        for (Appointment appointment : userAppointments) {
            cal.setTime(appointment.getDate());
            allDates.add(cal.getTime());
        }

        Set<Date> availableDates = new HashSet<>();
        for (int i = 0; i < 7; i++) {
            cal.setTime(new Date());
           cal.add(Calendar.DATE, i);
           // System.out.println(cal.getTime());
            for(Date d : allDates) {
                System.out.println(cal.getTime().getDay());
                System.out.println(d.getDay());
                if (d.getDay() != cal.getTime().getDay()) {
                    availableDates.add(cal.getTime());
                }
            }
        }


        List<Date> sortedAvailableDates = new ArrayList<>(availableDates);
        Collections.sort(sortedAvailableDates);
        return sortedAvailableDates;
    }

   /* @Override
    public List<Appointment>  checkAvailability(User user1, User user2, Date start, Date end) {
        Set<User> users = new HashSet<>(Arrays.asList(user1, user2));

        List<Appointment> appointments = appointmentRepository.findAppointmentsByUsersAndDate(users, start, end);
        return appointments;
    }*/


    @Override
    public List<Appointment> getAvailableAppointments(User user1, User user2, Date start, Date end) {
        // Créer un ensemble contenant les deux utilisateurs
        Set<User> users = new HashSet<>(Arrays.asList(user1, user2));

        // Obtenir la liste des rendez-vous disponibles
        List<Appointment> appointments = appointmentRepository.findByUsersInAndDateBetween(users, start, end);


        // Retourner la liste des rendez-vous disponibles
        return appointments;
    }




    @Override
    public  List<Appointment> getAvailablesDates(long user1, long user2) {
        List<Appointment> user1Appointments = appointmentRepository.findByUsers_userID(user1);
        List<Appointment> user2Appointments = appointmentRepository.findByUsers_userID(user2);
        List<Appointment>  availableAppointments = new ArrayList<>();
        List<Appointment> user1AppointmentsAv = new ArrayList<>();
        List<Appointment> user2AppointmentsAv = new ArrayList<>();
        System.out.println(user2Appointments.size());
        for(Appointment app11 : user1Appointments) {
            if(!app11.isAppointmentStatus()){
                user1AppointmentsAv.add(app11);
            }
        }

        for(Appointment app22 : user2Appointments) {
            System.out.println(app22.isAppointmentStatus());
            if(!app22.isAppointmentStatus()){
                user2AppointmentsAv.add(app22);
            }
        }

        for(Appointment app1 : user1AppointmentsAv){
            for(Appointment app2 : user2AppointmentsAv){

                if(app1.getDate().compareTo(app2.getDate()) == 0){
                    Appointment a1 = app1;
                    availableAppointments.add(a1);
                   // System.out.println(a1.getDate());
                }
            }
        }


        return availableAppointments.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public int getNombreRendezVousParuser(Long id) {
        return appointmentRepository.findByUsersUserID(id).size();
    }



    @Override
    public Map<String, Object> getAppointmentStatistics() {
        Map<String, Object> statistics = new HashMap<>();

        // nmbre total de rendez-vous
        long totalAppointments = appointmentRepository.count();
        statistics.put("totalAppointments", totalAppointments);

        // nmbre de rendez-vous pour chaque utilisateur
        List<Object[]> appointmentsPerUser = appointmentRepository.countAppointmentsPerUser();
        Map<String, Long> appointmentsPerUserMap = new HashMap<>();
        for (Object[] result : appointmentsPerUser) {
            appointmentsPerUserMap.put(result[0].toString(), (Long) result[1]);
        }
        statistics.put("appointmentsPerUser", appointmentsPerUserMap);

        // nmbre de rendez-vous pour chaque annonce
        List<Object[]> appointmentsPerAnnouncement = appointmentRepository.countAppointmentsPerAnnouncement();
        Map<String, Long> appointmentsPerAnnouncementMap = new HashMap<>();
        for (Object[] result : appointmentsPerAnnouncement) {
            appointmentsPerAnnouncementMap.put(result[0].toString(), (Long) result[1]);
        }
        statistics.put("appointmentsPerAnnouncement", appointmentsPerAnnouncementMap);

        // Moyenne de rendez-vous par utilisateur
        double averageAppointmentsPerUser = appointmentRepository.averageAppointmentsPerUser();
        statistics.put("averageAppointmentsPerUser", averageAppointmentsPerUser);

        return statistics;
    }



/*

@Override
    public Map<String, Integer> getTrendsByDayOfWeek() {
        List<Object[]> results = appointmentRepository.getTrendsByDayOfWeek();
        Map<String, Integer> trends = new HashMap<>();
        for (Object[] result : results) {
            String dayOfWeek = (String) result[0];
            Integer count = ((Number) result[1]).intValue();
            trends.put(dayOfWeek, count);
        }
        return trends;
    }
*/

  /*  @Override
    public List<Object[]> getTrendsByDayOfWeek() {
        return appointmentRepository.getTrendsByDayOfWeek();
    }*/



/*
    @Override
    public Appointment addAppointmentEtAffecterAUser(Appointment appointment, long userID) throws Exception
    {
        User user = userRepository.findById(userID).orElse(null);
        appointment.setUsers((Set<User>) user);

        // ******  Vérifier si les utilisateurs liés au rendez-vous existent
        if (appointment.getUsers() == null || appointment.getUsers().isEmpty()) {
            throw new Exception("Au moins un utilisateur doit être associé au rendez-vous.");
        }
        Set<User> users = new HashSet<>();
        for (User user1 : appointment.getUsers()) {
            if (user.getUserID() == Long.parseLong(null)) {
                throw new Exception("L'utilisateur associé au rendez-vous doit avoir un ID valide.");
            }
            Optional<User> existingUserOptional = userRepository.findById(user.getUserID());
            if (!existingUserOptional.isPresent()) {
                throw new Exception("L'utilisateur associé au rendez-vous est introuvable.");
            }
            User existingUser = existingUserOptional.get();
            users.add(existingUser);
        }
        appointment.setUsers(users);

        // Enregistrer l'entité dans la base de données
        return appointmentRepository.save(appointment);
    }
*/




       /* Announcement a= announcementRepository.findById(idAnnouncement).orElse(null);
        User requesterAppointment = userRepository.findById(idUser).orElse(null);
        User user = a.getUserAnnouncement();
        appointment.getUsers().add(user);
        appointment.getUsers().add(requesterAppointment);

        appointment.setAnnouncementApp(a);*/


}