import javax.swing.*;

/**
 * Created by natha on 2/12/2017.
 */
public class ConfigurePanel extends JPanel
{

    private JFrame frame;

    public ConfigurePanel(JFrame frame)
    {

        this.frame = frame;
        this.addButtons();

    }

    private void addButtons()
    {


        JButton cancel = new JButton("cancel");
        cancel.addActionListener(e ->
        {

            System.exit(0);

        });
        this.add(cancel);

        JButton back = new JButton("back");
        back.setEnabled(false);
        this.add(back);

        JButton next = new JButton("next");
        next.addActionListener(e ->
        {

            this.frame.setContentPane(new StagePanel(this.frame));
            this.frame.revalidate();

        });
        this.add(next);

        JButton finish = new JButton("finish");
        finish.setEnabled(false);
        this.add(finish);
/*
        bPoker.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                hands = deck.dealPokerHands(NUM_POKER_HANDS);
                result = deck.compareHands(hands[0], hands[1]);
                System.out.println(deck.showHands());
                repaint();
            }
        });
        this.add(bPoker);*/

    }

}
