
import java.net.*;
import java.io.*;
import java.util.*;




public class ProducerClient {
/*
Producer is the client which establishes connection
with Server Storage Space and is responsible for
creating different type of producer threads
*/
    public static final int PORT = 8655;
    protected boolean done = false;
    static int noofthreadsforproducer = 1;


    public static void main(String args[])
    {
        System.out.println("Connecting to port");

        if(args.length<1)
        {
            noofthreadsforproducer = 12;
        }
        else
        {
            try {
                if (Integer.valueOf(args[0].toString()) >= 0)
                    noofthreadsforproducer = Integer.valueOf(args[1].toString());
                else
                    throw new Negativenumberexception("No of producers should be positive Integer");

            }
            catch(Negativenumberexception e)
            {
                e.printStackTrace();
            }
            catch(NumberFormatException e)
            {
                System.out.println("It should be a Integer");
                System.exit(1);
            }


        }

        //Creating and starting a producer thread
        for (int i =0; i<noofthreadsforproducer;i++) {
            Thread prod = new Thread(new Producer(), "ProducerType1");

            Thread prod1 = new Thread(new Producer(), "ProducerType2");
            Thread prod2 = new Thread(new Producer(), "ProducerType3");


            prod.start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            prod1.start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            prod2.start();
        }



    }


}

class Producer extends ProducerClient implements Runnable {
    /**
     * Producer class:
     * It implements Runnable method and override run methods.
     * Producer class is responsible for producing the specified
     * no of items from multiple producer threads.
     */



    ServerSocket servsoc;


    @Override
    public void run() {
        /**
         * Overwrites the original run method from start class.
         */
        int n=0;
        while(true) {
            n+=1;
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
            Socket soc = null;
                soc = new Socket("XXX",PORT);


        //        BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
                PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
                System.out.println("Producer connected with server");
                System.out.println("Producer " + Thread.currentThread().getName() + " is running");
                out.println("Produce");


            out.close();
        //    in.close();
            soc.close();
        } catch (IOException e) {
            e.printStackTrace();
            }
        }

    }

}

