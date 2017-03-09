import javax.swing.*;

/**
 * Created by natha on 2/11/2017.
 */
public class Wizard
{

    private JFrame frame;

    public Wizard()
    {

        this.frame = new JFrame("CDEP Wizard");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(1200, 900);
        this.frame.setResizable(false);
        this.frame.setContentPane(new ConfigurePanel(this.frame));

        this.frame.setVisible(true);

    }

    public static void main(String[] args)
    {

        Wizard wizard = new Wizard();

    }

}
