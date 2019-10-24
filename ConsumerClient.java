import java.net.*;
import java.io.*;
import java.util.*;

public class ConsumerClient{
    /**
     * Consumer is the client which establishes
     * the connection with server storage space
     * and is responsible for creating
     * and running threads for consumer
     */
    static int noofthreadsforconsumer = 0;

    public static final int PORT = 8655;


    public static void main(String args[])
    {
        if(args.length<1)
        {
            noofthreadsforconsumer = 10;
        }
        else
        {
            try {
                if (Integer.valueOf(args[0].toString()) >= 0)
                    noofthreadsforconsumer = Integer.valueOf(args[0].toString());
                else
                    throw new Negativenumberexception("No of Consumer should be positive Integer");

            }
            catch(Negativenumberexception e)
            {

            }
            catch(NumberFormatException e)
            {
                System.out.println("It should be a Integer");
                System.exit(1);
            }


        }

        //Creating and starting a Consumer thread
        for (int i =0; i<noofthreadsforconsumer;i++) {
            Thread cons = new Thread(new Consumer(), "consumer"+i);
            cons.start();
        }
    }
}


    class Consumer extends ConsumerClient  implements Runnable {
        /**
         * Consumer class:
         * It implements Runnable method and override run methods.
         * Consumer class is responsible for consuming the specified
         * no of items from multiple consumer threads.
         */

        //@Override
        public void run() {
            /**
             * Overwrites the original run method from start class.
             */
            Socket soc = null;
            try {
                int n=0;
                while (true) {
                    n+=1;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                soc = new Socket("XXX",PORT);
                PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
                System.out.println("Consumer connected with server");
                System.out.println("Consumer " + Thread.currentThread().getName() + " is running");
                String removeditems = "";
                out.println("Consume");
                out.close();
                soc.close();
            }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
