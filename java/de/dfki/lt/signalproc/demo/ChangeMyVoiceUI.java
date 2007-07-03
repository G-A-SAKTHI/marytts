/*
 * ChangeMyVoiceUI.java
 *
 * Created on June 21, 2007, 7:52 AM
 */

package de.dfki.lt.signalproc.demo;

import java.awt.Container;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.awt.Point;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import de.dfki.lt.signalproc.FFT;
import de.dfki.lt.signalproc.process.FrameOverlapAddSource;
import de.dfki.lt.signalproc.process.InlineDataProcessor;
import de.dfki.lt.signalproc.process.LPCWhisperiser;
import de.dfki.lt.signalproc.process.Robotiser;
import de.dfki.lt.signalproc.process.Chorus;
import de.dfki.lt.signalproc.process.VocalTractScalingProcessor;
import de.dfki.lt.signalproc.process.VocalTractScalingSimpleProcessor;
import de.dfki.lt.signalproc.process.VocalTractModifier;
import de.dfki.lt.signalproc.filter.*;
import de.dfki.lt.signalproc.util.AudioDoubleDataSource;
import de.dfki.lt.signalproc.util.BufferedDoubleDataSource;
import de.dfki.lt.signalproc.util.DDSAudioInputStream;
import de.dfki.lt.signalproc.util.DoubleDataSource;
import de.dfki.lt.signalproc.util.MathUtils;
import de.dfki.lt.signalproc.util.SignalProcUtils;
import de.dfki.lt.signalproc.demo.OnlineAudioEffects;
import de.dfki.lt.signalproc.display.FunctionGraph;

/**
 *
 * @author  oytun.turk
 */

public class ChangeMyVoiceUI extends javax.swing.JFrame {
    private int targetIndex;
    private boolean bStarted;
    OnlineAudioEffects online;
    TargetDataLine microphone;
    SourceDataLine loudspeakers;
    VoiceModificationParameters modificationParameters;
    String [] targetNames = { "Robot", 
                              "Whisper", 
                              "Dwarf1",
                              "Dwarf2",
                              "Ogre1",
                              "Ogre2",
                              "Giant1",
                              "Giant2",
                              "Ghost",
                              "Stadium",
                              "Jet Pilot", 
                              "Old Radio", 
                              "Telephone"
                              }; 
    
    String [] inputNames = {"Streaming Audio",
                            "Record New File...",
                            "Browse for Input File...",
                            "Built-in Text-To-Speech Output 1",
                            "Built-in Text-To-Speech Output 2",
                            "Built-in Text-To-Speech Output 3" 
    };
    
    /** Creates new form ChangeMyVoiceUI */
    public ChangeMyVoiceUI() {
        microphone = null;
        loudspeakers = null;
        targetIndex = -1;
        initComponents();
        modificationParameters = new VoiceModificationParameters();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jButtonExit = new javax.swing.JButton();
        jComboBoxTargetVoice = new javax.swing.JComboBox();
        jLabelTargetVoice = new javax.swing.JLabel();
        jButtonAdd = new javax.swing.JButton();
        jButtonStart = new javax.swing.JButton();
        jButtonDel = new javax.swing.JButton();
        jButtonPlay = new javax.swing.JButton();
        jLabelLow = new javax.swing.JLabel();
        jScrollList = new javax.swing.JScrollPane();
        jListInput = new javax.swing.JList();
        jLabelChangeAmount = new javax.swing.JLabel();
        jLabelHigh = new javax.swing.JLabel();
        jSliderAmount = new javax.swing.JSlider();
        jLabelInput = new javax.swing.JLabel();
        jButtonRec = new javax.swing.JButton();
        jLabelMedium = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Change My Voice");
        setResizable(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jButtonExit.setText("Exit");
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });

        jComboBoxTargetVoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTargetVoiceActionPerformed(evt);
            }
        });

        jLabelTargetVoice.setText("Target Voice");
        jLabelTargetVoice.setName("");

        jButtonAdd.setText("Add");

        jButtonStart.setText("Start");
        jButtonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartActionPerformed(evt);
            }
        });

        jButtonDel.setText("Del");

        jButtonPlay.setText("Play");

        jLabelLow.setText("Low");

        jListInput.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollList.setViewportView(jListInput);

        jLabelChangeAmount.setText("Change Amount");

        jLabelHigh.setText("High");

        jSliderAmount.setMajorTickSpacing(50);
        jSliderAmount.setMinorTickSpacing(5);
        jSliderAmount.setPaintTicks(true);

        jLabelInput.setText("Input");

        jButtonRec.setText("Rec");

        jLabelMedium.setText("Medium");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(150, 150, 150)
                        .add(jButtonExit))
                    .add(layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabelChangeAmount)
                                    .add(jLabelTargetVoice))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                        .add(jLabelLow)
                                        .add(104, 104, 104)
                                        .add(jLabelMedium)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .add(jLabelHigh))
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, jSliderAmount, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, jComboBoxTargetVoice, 0, 278, Short.MAX_VALUE)))
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabelInput))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 18, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jScrollList, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(56, 56, 56)
                        .add(jButtonAdd)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jButtonStart, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                .add(jButtonRec)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jButtonPlay)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButtonDel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 105, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(43, 43, 43)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jComboBoxTargetVoice, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabelTargetVoice))
                .add(15, 15, 15)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jSliderAmount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(8, 8, 8)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabelLow)
                            .add(jLabelHigh)
                            .add(jLabelMedium)))
                    .add(jLabelChangeAmount))
                .add(14, 14, 14)
                .add(jLabelInput)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollList, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 225, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButtonRec)
                    .add(jButtonPlay)
                    .add(jButtonDel)
                    .add(jButtonAdd))
                .add(25, 25, 25)
                .add(jButtonStart, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(28, 28, 28)
                .add(jButtonExit)
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
    System.exit(0);
    }//GEN-LAST:event_jButtonExitActionPerformed

    private void jComboBoxTargetVoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTargetVoiceActionPerformed
        targetIndex = jComboBoxTargetVoice.getSelectedIndex();
        if (targetNames[targetIndex]=="Telephone")
            modificationParameters.fs = 8000;
        else
            modificationParameters.fs = 16000;
    }//GEN-LAST:event_jComboBoxTargetVoiceActionPerformed
    
    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        
    }//GEN-LAST:event_formMouseClicked

    private void jButtonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartActionPerformed
        if (!bStarted) { 
            getParameters();
            changeVoice();
            
            jButtonStart.setText("Stop");
        }
        else {
            bStarted = false;
            online.requestStop();
            
            //Close the source and the target datalines to be able to use them repeatedly
            microphone.close();
            microphone = null;
            loudspeakers.close();
            loudspeakers = null;
            //

            jButtonStart.setText("Start");
        }
    }//GEN-LAST:event_jButtonStartActionPerformed

    /* This function gets the modification parameters from the GUI
     * and fills in the modificationParameters object
    */ 
    private void getParameters() {
        targetIndex = jComboBoxTargetVoice.getSelectedIndex();
    }
    
    /*This function opens source and target datalines and starts real-time voice modification  
     * using the parameters in the modificationParameters object
     */ 
    private void changeVoice() {
        bStarted = true;
        int channels = 1;
            
        AudioFormat audioFormat = null;

        audioFormat = new AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED, modificationParameters.fs, 16, channels, 2*channels, modificationParameters.fs,
                false);

        if (microphone != null)
            microphone.close();

        try {
            DataLine.Info info = new DataLine.Info(TargetDataLine.class,
                    audioFormat);
            
            microphone = (TargetDataLine) AudioSystem.getLine(info);
            microphone.open(audioFormat);
            System.out.println("Microphone format: " + microphone.getFormat());
            
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.exit(1);
        }

        if (loudspeakers != null)
            loudspeakers.close();
        
        try {
            DataLine.Info info = new DataLine.Info(SourceDataLine.class,
                    audioFormat);
            loudspeakers = (SourceDataLine) AudioSystem.getLine(info);
            loudspeakers.open(audioFormat);
            System.out.println("Loudspeaker format: " + loudspeakers.getFormat());
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        // Choose an audio effect
        InlineDataProcessor effect = null;
        
        if (targetNames[targetIndex]=="Robot")
        {  
            effect = new Robotiser.PhaseRemover(4096);
        }
        else if (targetNames[targetIndex]=="Whisper")
        {  
            effect = new LPCWhisperiser(SignalProcUtils.getLPOrder((int)modificationParameters.fs));
        }
        else if (targetNames[targetIndex]=="Dwarf1") //Using freq. domain LP spectrum modification
        {  
            double [] vscales = {1.5};
            int p = SignalProcUtils.getLPOrder((int)modificationParameters.fs);
            int fftSize = Math.max(SignalProcUtils.getDFTSize((int)modificationParameters.fs), 1024);
            effect = new VocalTractScalingProcessor(p, (int)modificationParameters.fs, fftSize, vscales);
        }
        else if (targetNames[targetIndex]=="Dwarf2") //Using freq. domain DFT magnitude spectrum modification
        {  
            double [] vscales = {1.5};
            effect = new VocalTractScalingSimpleProcessor(1024, vscales);
        }
        else if (targetNames[targetIndex]=="Ogre1") //Using freq. domain LP spectrum modification
        { 
            double [] vscales = {0.85};            
            int p = SignalProcUtils.getLPOrder((int)modificationParameters.fs);
            int fftSize = Math.max(SignalProcUtils.getDFTSize((int)modificationParameters.fs), 1024);
            effect = new VocalTractScalingProcessor(p, (int)modificationParameters.fs, fftSize, vscales);
        }
        else if (targetNames[targetIndex]=="Ogre2") //Using freq. domain DFT magnitude spectrum modification
        { 
            double [] vscales = {0.85};
            effect = new VocalTractScalingSimpleProcessor(1024, vscales);
        }
        else if (targetNames[targetIndex]=="Giant1") //Using freq. domain LP spectrum modification
        {  
            double [] vscales = {0.75};
            int p = SignalProcUtils.getLPOrder((int)modificationParameters.fs);
            int fftSize = Math.max(SignalProcUtils.getDFTSize((int)modificationParameters.fs), 1024);
            effect = new VocalTractScalingProcessor(p, (int)modificationParameters.fs, fftSize, vscales);
        }
        else if (targetNames[targetIndex]=="Giant2") //Using freq. domain DFT magnitude spectrum modification
        {  
            double [] vscales = {0.75};
            effect = new VocalTractScalingSimpleProcessor(1024, vscales);
        }
        else if (targetNames[targetIndex]=="Ghost")
        {
            int [] delaysInMiliseconds = {100, 200, 300};
            double [] amps = {0.8, -0.7, 0.9};
            effect = new Chorus(delaysInMiliseconds, amps, (int)(modificationParameters.fs));
        }
        else if (targetNames[targetIndex]=="Stadium")
        {
            int [] delaysInMiliseconds = {366, 500};
            double [] amps = {0.54, -0.10};
            effect = new Chorus(delaysInMiliseconds, amps, (int)(modificationParameters.fs));
        }
        else if (targetNames[targetIndex]=="Jet Pilot")
        {  
            double normalizedCutOffFreq1 = 500.0/modificationParameters.fs;
            double normalizedCutOffFreq2 = 2000.0/modificationParameters.fs;
            effect = new BandPassFilter(normalizedCutOffFreq1, normalizedCutOffFreq2, true);
        }
        else if (targetNames[targetIndex]=="Telephone")
        {  
            double normalizedCutOffFreq1 = 300.0/modificationParameters.fs;
            double normalizedCutOffFreq2 = 3400.0/modificationParameters.fs;
            effect = new BandPassFilter(normalizedCutOffFreq1, normalizedCutOffFreq2, true);
        }
        else if (targetNames[targetIndex]=="Old Radio")
        {  
            double normalizedCutOffFreq = 3000.0/modificationParameters.fs;
            effect = new LowPassFilter(normalizedCutOffFreq, true);
        }
        //            

        // Create the output thread and make it run in the background:
        if (effect!=null)
        {
            online = new OnlineAudioEffects(effect, microphone, loudspeakers);
            online.start();
        }
    }
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        int i;
        
        //Move the window to somewhere closer to middle o screen
        Point p = this.getLocation();
        Dimension d = this.getSize();
        p.x = (int)(0.5*(1500-d.getWidth()));
        p.y = (int)(0.5*(1000-d.getHeight()));
        this.setLocation(p);
        //
        
        bStarted = false;
        
        //Fill-in target voice combo-box
        for (i=0; i<targetNames.length; i++) {
            jComboBoxTargetVoice.addItem(targetNames[i]);
        }
        //
        
        //Fill-in input combo-box
        jListInput.setListData(inputNames);
        //
    }//GEN-LAST:event_formWindowOpened
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChangeMyVoiceUI().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDel;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonPlay;
    private javax.swing.JButton jButtonRec;
    private javax.swing.JButton jButtonStart;
    private javax.swing.JComboBox jComboBoxTargetVoice;
    private javax.swing.JLabel jLabelChangeAmount;
    private javax.swing.JLabel jLabelHigh;
    private javax.swing.JLabel jLabelInput;
    private javax.swing.JLabel jLabelLow;
    private javax.swing.JLabel jLabelMedium;
    private javax.swing.JLabel jLabelTargetVoice;
    private javax.swing.JList jListInput;
    private javax.swing.JScrollPane jScrollList;
    private javax.swing.JSlider jSliderAmount;
    // End of variables declaration//GEN-END:variables
    
}
