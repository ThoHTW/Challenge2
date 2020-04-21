package filepersistenceTest;

import filepersistence.WriteAndReadDataSet;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class WriteAndReadDataSetTest {

    @Test
    public void gutTest1() throws Exception {
    //correcte verarbeitung von richtigen werten
        WriteAndReadDataSet test = new WriteAndReadDataSet();
        String name = "MySensor";
        long time = 100;
        float[] values = new float[2];
        values[0] = (float)4;
        values[1] = (float)3;
        String[] exceptedOutput = {"MySensor","100","[4.0, 3.0]"};
        String[] methodOutput = {name, Long.toString(time), Arrays.toString(values)};
        test.saveData(name, time, values);
        Assert.assertArrayEquals(exceptedOutput, methodOutput);

    }

    @Test
    public void randTest1() throws Exception{
    //Exception bei überschreitung von randwerten im positiven
        WriteAndReadDataSet test = new WriteAndReadDataSet();
        String name = "MySensor";
        long time = Long.MAX_VALUE;
        long erwartet = Long.MAX_VALUE;
        float[] values = {Float.MAX_VALUE};
        float[] erwartet2 = {Float.MAX_VALUE};
        String[] exceptedOutput = {Long.toString(erwartet), Arrays.toString(erwartet2)};
        String[] methodOutput = {Long.toString(time), Arrays.toString(values)};
        test.saveData(name, time, values);
        Assert.assertArrayEquals(exceptedOutput, methodOutput);
    }

    @Test
    public void randTest2() throws Exception{
    //exception bei überschreitung von randwerten im negativen
        WriteAndReadDataSet test = new WriteAndReadDataSet();
        String name = "MySensor";
        long time = Long.MIN_VALUE;
        long erwartet = Long.MIN_VALUE;
        float[] values = {Float.MIN_VALUE};
        float[] erwartet2 = {Float.MIN_VALUE};
        String[] exceptedOutput = {Long.toString(erwartet), Arrays.toString(erwartet2)};
        String[] methodOutput = {Long.toString(time), Arrays.toString(values)};
        test.saveData(name, time, values);
        Assert.assertArrayEquals(exceptedOutput, methodOutput);
    }

    @Test
    public void schlechtTest1(){
        WriteAndReadDataSet test = new WriteAndReadDataSet();
        try{
            String name = "MySensor";
            long time = Long.MAX_VALUE+1;
            float[] values = {Float.MAX_VALUE+1};
            test.saveData(name, time,values);
            Assert.fail();
        }catch (Exception e){
            System.out.print("Das sollte passieren");
        }
    }

    @Test
    public void schlechtTest2(){
        WriteAndReadDataSet test = new WriteAndReadDataSet();
        try{
            String name = "MySensor";
            long time = Long.MIN_VALUE-1;
            float[] values = {Float.MIN_VALUE-1};
            test.saveData(name, time,values);
            Assert.fail();
        }catch (Exception e){
            System.out.println("Das sollte auch passieren");
        }
    }
}
