import java.util.Scanner;

public class Main {
  public static void main(String [] args){
//    System.out.println("Hellooooooo there.");
    Controller controller = new Controller(System.in, System.out);
    controller.run(new CalcImpl());
  }
}
