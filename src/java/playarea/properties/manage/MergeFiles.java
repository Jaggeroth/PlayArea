package playarea.properties.manage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MergeFiles {

  private static final String NERPS_PROP_FILE = "nerps_it.properties";
  private static final String JAPPY_PROP_FILE = "jappy_it.properties";
  private static final String NEW_PROP_FILE   = "src\\resources\\new_it.properties";
  private final Properties nerpsProp = new Properties();
  private final Properties jappyProp = new Properties();
  private final Properties newProp = new Properties();

  private void initialiseProperties() {
    InputStream nerpsIn = this.getClass().getClassLoader().getResourceAsStream(NERPS_PROP_FILE);
    InputStream jappyIn = this.getClass().getClassLoader().getResourceAsStream(JAPPY_PROP_FILE);
    try {
      nerpsProp.load(nerpsIn);
      jappyProp.load(jappyIn);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }
  
  private void moveNewToOld() {
    for (String prop : nerpsProp.stringPropertyNames()) {
      newProp.setProperty(prop,  nerpsProp.getProperty(prop));
    }
    
    for(String prop : jappyProp.stringPropertyNames()) {
      String jappyValue = jappyProp.getProperty(prop);
      if (nerpsProp.containsKey(prop)) {
        String nerpsValue = nerpsProp.getProperty(prop);
        if (!jappyValue.equals(nerpsValue)) {
          System.out.println("UPDATE " + prop);
          newProp.setProperty(prop, jappyValue);
        }
      } else {
        System.out.println("NEW " + prop);
        newProp.setProperty(prop, jappyValue);
      }
    }
    File file = new File(NEW_PROP_FILE);
    FileOutputStream fileOut;
    try {
      fileOut = new FileOutputStream(file);
      newProp.store(fileOut, "Merge Jappy commit 945221e with Nerps");
      fileOut.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    System.out.println("START");
    MergeFiles mf = new MergeFiles();
    mf.initialiseProperties();
    mf.moveNewToOld();
    System.out.println("END");
  }

}
