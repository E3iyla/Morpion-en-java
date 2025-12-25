package view;
import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

//jouer un fichier son (.wav)
public class PlayWave extends Thread {

    private String filename;
    private Position curPosition;

    // Taille de la mémoire tampon (buffer) pour lire le fichier petit à petit
    private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb
    enum Position {LEFT, RIGHT, NORMAL};

    public PlayWave(String wavfile) {
        filename = wavfile;
        curPosition = Position.NORMAL;
    }

    public void run() {
        java.net.URL soundURL= getClass().getResource("/"+ filename);
        if(soundURL== null){
            System.err.println ("Fichier son introuvable dans le JAR : " + filename);
            return;
        }

        System.out.println("Fichier trouvé : " + soundUrl);

        AudioInputSysteme audioInputStream = null; 
        
        try{
            audioInputStream = AudioSystem.getAudioInputStream(soundURL);
        }catch (UnsupportedAudioFileException e1) {
            e1.printStackTrace();
            return;
        }catch (IOException e1) {
            e1.printStackTrace();
            return;
        }
        
        AudioFormat format = audioInputStream.getFormat(); // recup le format du son
        SourceDataLine auline = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

        try {
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        // gestion de la balance gauche droite
        if(auline.isControlSupported(FloatControl.Type.PAN)){
            FloatControl pan = (FloatControl) auline.getControl(FloatControl.Type.PAN);
            if (curPosition == Position.RIGHT) {
                pan.setValue(1.0f);
            }else if (curPosition == Position.LEFT) {
                pan.setValue(-1.0f);
            }
        }

        auline.start(); // commencer la lecture
        int nBytesRead = 0;
        byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];

        try {
            // boucle lecture du son
            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0) {
                    auline.write(abData, 0, nBytesRead);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
            return;
        }finally{
            // vidage du buffer nettoyage final
            auline.drain();
            auline.close();
        }
    }


}
