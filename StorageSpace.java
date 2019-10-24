/**
 * ConsumerProducer.java
 * Version:
 *     1.0
 *
 * Revisions:
 *     Implements Producer consumer example using multithreading
 *     over TCP/IP connection.
 *     Storage space acts a server with accepting
 *     requests from client Producer and Consumer
 */


import java.io.*;
import java.net.*;

public class StorageSpace{
    /**
     * Class StorageSpace acts as a server
     * it defines all static global variables.
     * It is responsible for accepting requests from
     * client and processing it
     */

    static int counter1 = 0;
    static int counter2 = 0;
    static int counter3 = 0;

    static int sharedSpace = 30;
    static int putitems = 6;
    static int consumeitemstype1 = 3;
    static int consumeitemstype2 = 5;
    static int consumeitemstype3 = 2;
    static int Listsize = 0;
    static boolean producerFinished = false;
    static boolean consumerFinished = false;


    int consumer1 = 0;
    static Socket s = null;
    static ServerSocket ss1 = null;

    public static void main(String args[]) {
        StorageSpace obj = new StorageSpace();
        while (true) {
            try {
                /**
                Creates socket of class serversocket class
                wit port number consistent with client whose
                requests to be adopted
                 */
                ss1 = new ServerSocket(8655);
                s = ss1.accept();
                BufferedReader is = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String line = is.readLine();

                    if (line.equals("Produce")) {
                        if (obj.getcounterforpoducer1() + putitems >= sharedSpace || obj.getcounterforpoducer2() + putitems >= sharedSpace || obj.getcounterforpoducer3() + putitems >= sharedSpace) {
                            //producerFinished = true;
                            System.out.println("Current Storage space " + obj.getcounterforpoducer1() + " " + obj.getcounterforpoducer2()+" " + obj.getcounterforpoducer3());
                            System.out.println("ProducerType1 goes in waiting state");

                            is.close();
                            s.close();
                            ss1.close();
                        } else {
                             obj.setProducer1();
                             obj.setProducer2();
                             obj.setProducer3();

                                System.out.println("Producer produced " + putitems + " items");
                                System.out.println("Current Storage space " + obj.getcounterforpoducer1() + " " + obj.getcounterforpoducer2()+" " + obj.getcounterforpoducer3());
                            is.close();
                            s.close();
                            ss1.close();
                        }
                    } else {
                        if (obj.getcounterforpoducer1() - consumeitemstype1 <= 0 || obj.getcounterforpoducer2() - consumeitemstype2 <=0  || obj.getcounterforpoducer3() - consumeitemstype3 <=0)  {
                            //consumerFinished = true;
                            System.out.println("Current Storage space " + obj.getcounterforpoducer1() +" "+ obj.getcounterforpoducer2() +" " + obj.getcounterforpoducer3());
                            System.out.println("Consumer goes in waiting state");
                            is.close();
                            s.close();
                            ss1.close();
                        } else {
                                obj.setConsumer1();
                                obj.setConsumer2();
                                obj.setConsumer3();

                                System.out.println("Consumer consumed " + consumeitemstype1 + "," + consumeitemstype2 + " , "+ consumeitemstype3 + "  items");
                                System.out.println("Current Storage space " + obj.getcounterforpoducer1() +" "+ obj.getcounterforpoducer2() +" " + obj.getcounterforpoducer3());
                            is.close();
                            s.close();
                            ss1.close();
                        }

                    }


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public void setProducer1() {
        counter1 = counter1 + putitems;
    }

    public int getcounterforpoducer1() {
        /**
         * Sets the counter for producer type 1
         */
        return this.counter1;
    }


    public void setConsumer1() {
        /**
         * Sets the counter for Consumer type 1
         */
        counter1 = counter1 - consumeitemstype1;
    }





    public int getcounterforpoducer2()
    /**
     * Sets the counter for producer type 2
     */
    {
        return this.counter2;
    }


    public int getcounterforpoducer3()
    /**
     * Sets the counter for producer type 3
     */
    {
        return this.counter3;
    }


    public void setProducer2()
    /**
     * Increments the counter for Producer type2
     */
    {
        counter2 = counter2 + putitems;
    }

    public void setProducer3()
    /**
     * Increments the counter for Producer type 3
     */
    {
        counter3 = counter3 + putitems;
    }


    public void setConsumer2()
    /**
     * decrements the Counter of type 2
     */
    {
        counter2 = counter2 - consumeitemstype2;
    }
    public void setConsumer3()
    /**
     * Decrements the counter of type 3
     */
    {
        counter3 = counter3 - consumeitemstype3;
    }

}


