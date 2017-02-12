import javax.swing.*;

/**
 * Created by natha on 2/12/2017.
 */
public class StagePanel extends JPanel
{

    private JFrame frame;

    public StagePanel(JFrame frame)
    {

        this.frame = frame;
        this.addButtons();

    }


    @SuppressWarnings("Duplicates")
    private void addButtons()
    {

        JButton cancel = new JButton("cancel");
        cancel.addActionListener(e ->
        {

            System.exit(0);

        });
        this.add(cancel);

        JButton back = new JButton("back");
        back.addActionListener(e ->
        {

            this.frame.setContentPane(new ConfigurePanel(this.frame));
            this.frame.revalidate();
            //Wizard.swapPanels(this.frame, new ConfigurePanel(this.frame));

        });
        this.add(back);

        JButton next = new JButton("next");
        next.setEnabled(false);
        this.add(next);

        JButton finish = new JButton("finish");
        finish.addActionListener(e ->
        {

            System.exit(0);

        });
        this.add(finish);

    }

}
