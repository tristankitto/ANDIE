package cosc202.andie;
import javax.swing.*;
//import java.awt.*;
import java.awt.event.*;

public class Theme extends JFrame implements ActionListener{
    private JRadioButton windows, metal, motif ;
    private ButtonGroup bg;
    
    public theme(){
    }
    
    public void themeTest(){
    windows = new JRadioButton("Windows");
      windows.addActionListener(this);
      metal = new JRadioButton("Metal");
      metal.addActionListener(this);
      motif = new JRadioButton("Motif");
      motif.addActionListener(this);
      bg = new ButtonGroup();
      bg.add(windows);
      bg.add(metal);
      bg.add(motif);
        Andie.frame.add(windows);
       Andie.frame.add(metal);
        Andie.frame.add(motif);
        Andie.frame.setVisible(true);
    }
    @Override
   public void actionPerformed(ActionEvent ae) {
    String LAF;
    if(ae.getSource() == windows)
       LAF = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    else if(ae.getSource() == metal)
       LAF = "javax.swing.plaf.metal.MetalLookAndFeel";
    else
       LAF = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
    try {
       UIManager.setLookAndFeel(LAF);
       SwingUtilities.updateComponentTreeUI(this);
    } catch (Exception e) {
       System.out.println("Error setting the LAF..." + e);
    }
 
    
    }      
}