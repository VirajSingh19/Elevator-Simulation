import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
class Screen {

  static int floor;
  static boolean rev = false;
  static int direction;

  static void clearV()throws Exception {
    FileWriter w = new FileWriter("v.txt");
    w.write("");
    w.flush();
    w.close();
  }
  static void clearKiosk()throws Exception {
    FileWriter w = new FileWriter("kiosk.txt");
    w.write("");
    w.flush();
    w.close();
  }




  static List<Floor> list = new ArrayList<>();
  static Scanner in = new Scanner(System.in);

  public static void main(String args[])throws Exception
  {

    floor = (int) (Math.random() * 10 + 1);
    System.out.println("The elevator is now on floor " +floor);
    System.out.print("Which floor are you at now (0-10) where 0 = basement(Enter in v.txt): ");
    while(true){

          Scanner b = new Scanner(new File("v.txt"));
          while(b.hasNext()){
          String raw_inp  = b.next();
          if(raw_inp.length()!=0){
            MoveElevator(Integer.parseInt(raw_inp));
            clearV();

          while(list.size()!=0){
            int z = list.get(0).floor;
            MoveElevator(z);
          }


          }
      }
    }

  }





  public static void MoveElevator(int target_floor)throws Exception
  {
      if( target_floor > floor )
          direction = 1;
      else
          direction = -1;

      while(target_floor != floor)
      {
          external_btn();
          target_floor=list.get(0).floor;
          floor += direction;

          if(direction==1)
            System.out.println(floor+"↑");
          else
            System.out.println(floor+"↓");

          Thread.sleep(1000);
      }
      list.remove(0);
      clearV();
      System.out.println("The elevator has arrived on floor "+floor);
      System.out.println("Write in kios file entre a character in console to close doors");
      in.next();
      loadList();
      System.out.println(list);

  }






  static void loadList()throws Exception {
    Scanner k = new Scanner(new File("kiosk.txt"));
    while(k.hasNext())
    {
      String arr[] = k.nextLine().split("\\.");
      clearKiosk();
      Floor.curr = floor;
      for(String i:arr)
        list.add(new Floor(Integer.parseInt(i)));

        Collections.sort(list);
      break;
    }
  }

  static void external_btn()throws Exception {
    Scanner b = new Scanner(new File("v.txt"));
    while(b.hasNext()){
    String raw_inp  = b.next();
    if(raw_inp.length()!=0){
      int z = Integer.parseInt(raw_inp);

      Floor.curr=floor;

      list.add(new Floor(z));
      Collections.sort(list);

      System.out.println(list);
      clearV();
  }




}
}
}
