package pi.app.estatemarket.Services;

import pi.app.estatemarket.Entities.Appointment;
import pi.app.estatemarket.Entities.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IAppointmentService {
    Appointment addAppointment(Appointment appointment) throws Exception;

    List<Appointment> getAppointment();

    void deleteAppointment(Long id);

    Appointment updateAppointment(Appointment appointment, Long id) throws Exception;
    // public void affecterAppointmentToUser(Long IdAppointment);

    void affecterUserAppointment(Long IdAppointment, Long userID);


    //List<Appointment> findAppointmentsByUsersAndDate(User user1, User user2, Date start, Date end);

    List<Date> getAvailableDatesFinal(Long userId,Long userId2);

    List<Appointment> getAvailableAppointments(User user1, User user2, Date start, Date end);

    //boolean checkAvailability(User user1, User user2, Date start, Date end);
    //List<Appointment>  checkAvailability(User user1, User user2, Date start, Date end);
    List<Appointment> getAvailablesDates(long user1, long user2);

    /* Map<String, Long> getTrendsByDayOfWeek();*/

    //  List<Object[]> getTrendsByDayOfWeek();
    int getNombreRendezVousParuser(Long id);
    Map<String, Object> getAppointmentStatistics();

    // Map<String, Integer> getTrendsByDayOfWeek();


    // Appointment addAppointmentEtAffecterAUser(Appointment appointment, long userID) throws Exception;

    //List<Appointment>  findAppointmentsByUsersAndDate(User user1, User user2, Date start, Date end);



}