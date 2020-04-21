package filepersistence;

import java.io.*;

public class WriteAndReadDataSet implements SensorDataStorage{

    public static void main (String[] Args){
        WriteAndReadDataSet obj1 = new WriteAndReadDataSet();
        String name = "name";
        long time = System.currentTimeMillis();
        float[] values = {Float.MAX_VALUE+1, 7, 3};
        try {
            obj1.saveData(name, time, values);
        }catch (Exception e){
            System.out.println("saveDara konnte nicht ausgeführt werden");
        }
    }

    @Override
    public void saveData(String name, long time, float[] values) throws Exception{

        final String filename = "testText";
        // write three data set into a file
        // TODO: your job. use DataOutputStream / FileOutputStream
        //Outputstream eröffnen
        OutputStream os;
        DataOutputStream dos = null;
        try {
            os = new FileOutputStream(filename);
            dos = new DataOutputStream(os);
        } catch (FileNotFoundException ex) {
            System.err.println("couldn’t open file - fatal");
        }
        //alle Messwerte eintragen
            try {
                assert dos != null;
                dos.writeUTF(name);
                if(time > Long.MAX_VALUE | time < Long.MIN_VALUE) throw new Exception();
                dos.writeLong(time);
                for (float value : values) {
                    if(value > Float.MAX_VALUE | value < Float.MIN_VALUE) throw new Exception();
                    dos.writeFloat(value);
                }
            } catch (IOException ex) {
                System.err.println("couldn’t write data (fatal)");
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
        }
        System.out.println("Aufbau: Sensorname/Zeit/Wert");
            try {
                assert dis != null;
                String a = dis.readUTF();
                System.out.print(a+" ");
                long c = dis.readLong();
                System.out.print(c+" ");
                for (int j = 0; j < values.length; j++) {
                    float e = dis.readFloat();
                    System.out.print(e+" ");
                }
                System.out.print("\n");
            } catch (IOException ex) {
                System.err.println("couldn’t write data (fatal)");
            }
        }
    }


