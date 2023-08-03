import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class BookMyShow {
    ArrayList<Theater> theaters;
    static HashMap<String,ArrayList<Show>> movieMap;

    private void generateMovieMap(){
        for (Theater theater :theaters) {
            ArrayList<Show> showArray = theater.getShows();
            for(Show show : showArray) {
                if (show != null) {
                    if (movieMap.containsKey(show.getMovie().getName())) {
                        movieMap.get(show.getMovie().getName()).add(show);
                    } else {
                        ArrayList<Show> movieShowList = new ArrayList<>();
                        movieShowList.add(show);
                        movieMap.put(show.getMovie().getName(), movieShowList);
                    }
                }
            }
        }
    }
    public BookMyShow(ArrayList<Theater> theaters) {
        this.theaters = theaters;
        this.movieMap = new HashMap<>();
        generateMovieMap();
        System.out.println(movieMap);
    }
    public static ArrayList<Show> searchShows(String movieName){
        if (movieMap.containsKey(movieName)){
            return movieMap.get(movieName);
        }
        else
            return null;
    }

    public static void main(String[] args) {
         GuestUser piyush = new GuestUser("Piyush");
         RegisteredUser ayush = new RegisteredUser("Ayush");
         RegisteredUser saurabh = new RegisteredUser("Saurabh");
         Movie ironMan = new Movie("Iron Man", Language.ENGLISH,Genre.ACTION);
         Movie avengers = new Movie("Avengers: End Game", Language.ENGLISH,Genre.ACTION);
          walkToRemember = new Movie("The Walk To Remember", Language.ENGLISH,Genre.ROMANCE);

        
        Movie housefull = new Movie("HouseFull 2", Language.HINDI,Genre.COMEDY);

       
        Theater pvr_gip = new Theater("PVR","GIP Noida",30);

      
        Theater big_cinema = new Theater("Big Cinema","Sector 137 Noida",40);




        // Creating four shows for movies
        Show show1=null, show2=null, show3=null, show4=null;
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");

        try {
        
            String dateInString = "Friday, Jun 7, 2020 09:00:00 AM";
            Date date = formatter.parse(dateInString);
            show1 = new Show(date,ironMan,pvr_gip);

           
            dateInString = "Friday, Jun 7, 2020 12:00:00 PM";
            date = formatter.parse(dateInString);
            show2 = new Show(date,housefull,pvr_gip);

           
            dateInString = "Friday, Jun 7, 2020 09:00:00 AM";
            date = formatter.parse(dateInString);
            show3 = new Show(date,walkToRemember,big_cinema);

            
            dateInString = "Friday, Jun 7, 2020 12:00:00 PM";
            date = formatter.parse(dateInString);
            show4 = new Show(date,walkToRemember,big_cinema);

        } catch (ParseException e) {
            e.printStackTrace();
        }

       

   
        ArrayList<Theater> theaterArrayList= new ArrayList<>();
        theaterArrayList.add(pvr_gip);
        theaterArrayList.add(big_cinema);
        BookMyShow bookMyShow = new BookMyShow(theaterArrayList);

     
        ArrayList<Show> searchedShow = BookMyShow.searchShows("The Walk To Remember");
       
        searchedShow --> [Show{id=3, showTime=Sun Jun 07 09:00:00 IST 2020,
        movie=The Walk To Remember, theater=Big Cinema, availableSeats=40},
        Show{id=4, showTime=Sun Jun 07 12:00:00 IST 2020, movie=The Walk To Remember,
        theater=Big Cinema, availableSeats=40}]
       

        Show bookingShow = searchedShow.get(0);

        // Ticket Booking Thread for the request made by AYUSH for 10 Seats
        TicketBookingThread t1 = new TicketBookingThread(bookingShow,ayush,10);

     
        TicketBookingThread t2 = new TicketBookingThread(bookingShow,saurabh,10);

        // Start both the Ticket Booking Threads for execution
        t1.start();
        t2.start();

        // Waiting till both the thread completes the execution
        try {
            t1.join();
            t2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t
        Ticket ayush_ticket = t1.getTicket();
        Ticket saurabh_ticket = t2.getTicket();

        // Printing their tickets
        System.out.println(t1.getTicket());
        System.out.println(t2.getTicket());

       
        TicketBookingThread t3 = new TicketBookingThread(bookingShow,ayush,15);

      
        TicketBookingThread t4 = new TicketBookingThread(bookingShow,saurabh,10);

       
        t3.start();
        t4.start();

    
        try {

            t4.join();
            t3.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        
        Ticket ayushNewTicket = t3.getTicket();
        Ticket saurabhNewTicket = t4.getTicket();

        System.out.println(ayushNewTicket);
        System.out.println(saurabhNewTicket);


    }
}
