package filepersistence;

import java.io.*;

public class WriteAndReadDataSet {

    public static void main(String[] args) {
        final String filename = "testFile.txt";
        // three example data sets
        String sensorName = "MyGoodOldSensor"; // does not change
        String abstand = "\n";
        String abstand2 = "/";

        long[] timeStamps = new long[3];
        timeStamps[0] = System.currentTimeMillis();
        timeStamps[1] = timeStamps[0] + 1; // milli sec later
        timeStamps[2] = timeStamps[1] + 1000; // second later

        float[][] values = new float[3][];
        // 1st measure .. just one value
        float[] valueSet = new float[1];
        values[0] = valueSet;
        valueSet[0] = (float) 1.5; // example value 1.5 degrees

        // 2nd measure .. just three values
        valueSet = new float[3];
        values[1] = valueSet;
        valueSet[0] = (float) 0.7;
        valueSet[1] = (float) 1.2;
        valueSet[2] = (float) 2.1;

        // 3rd measure .. two values
        valueSet = new float[2];
        values[2] = valueSet;
        valueSet[0] = (float) 0.7;
        valueSet[1] = (float) 1.2;

        // write three data set into a file
        // TODO: your job. use DataOutputStream / FileOutputStream
        //3 Messwerte
        //Outputstream eröffnen
        OutputStream os;
        DataOutputStream dos = null;
        try {
            os = new FileOutputStream(filename);
            dos = new DataOutputStream(os);
        } catch (FileNotFoundException ex) {
            System.err.println("couldn’t open file - fatal");
            System.exit(0); // brutal exception handling
        }
        //alle Messwerte eintragen
        for (int i = 0; i < 3;  i++){
            try{
                dos.writeUTF(sensorName);
                for(int j = 0; j < values[i].length; j++){
                    dos.writeUTF(abstand2);
                    dos.writeLong(timeStamps[j]);
                    dos.writeUTF(abstand2);
                    dos.writeFloat(values[i][j]);
                }
                dos.writeUTF(abstand);

            }catch (IOException ex){
                System.err.println("couldn’t write data (fatal)");
                System.exit(0);
            }
        }

        // read data from file and print to System.out
        // TODO: your job use DataInputStream / FileInputStream

        InputStream is;
        DataInputStream dis = null;
        try {
            is = new FileInputStream(filename);
            dis = new DataInputStream(is);
        } catch (FileNotFoundException ex) {
            System.err.println("couldn’t open file - fatal");
            System.exit(0);
        }
        System.out.println("Aufbau: Sensorname/Zeit/Wert/.../Zeit/Wert");
        for (int i = 0; i < 3;  i++){
            try{
                String a = dis.readUTF();
                System.out.print(a);
                for(int j = 0; j < values[i].length; j++){
                    String b = dis.readUTF();
                    System.out.print(b);
                    long c = dis.readLong();
                    System.out.print(c);
                    String d =dis.readUTF();
                    System.out.print(d);
                    float e = dis.readFloat();
                    System.out.print(e);
                }
                String f = dis.readUTF();
                System.out.print(f);

            }catch (IOException ex){
                System.err.println("couldn’t write data (fatal)");
                System.exit(0);
            }
        }
    }
}


