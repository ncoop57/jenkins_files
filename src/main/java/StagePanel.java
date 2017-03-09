import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by natha on 2/12/2017.
 */
public class StagePanel extends JPanel
{

    private JFrame frame;
    private Pipeline pipeline;

    public StagePanel(JFrame frame, Pipeline pipeline)
    {

        this.pipeline = pipeline;
        this.frame = frame;
        this.addButtons();

    }


    @SuppressWarnings("Duplicates")
    private void addButtons()
    {

        JCheckBox staticStage = new JCheckBox("Static Analysis");
        JCheckBox unitStage = new JCheckBox("Unit Testing");
        JCheckBox integrationStage = new JCheckBox("Integration Testing");

        staticStage.setMnemonic(KeyEvent.VK_C);
        unitStage.setMnemonic(KeyEvent.VK_M);
        integrationStage.setMnemonic(KeyEvent.VK_P);

        this.add(staticStage);
        this.add(unitStage);
        this.add(integrationStage);

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

        });
        this.add(back);

        JButton next = new JButton("next");
        next.setEnabled(false);
        this.add(next);

        JButton finish = new JButton("finish");
        finish.addActionListener(e ->
        {

            if (staticStage.isSelected())
            {

                this.pipeline.setStage("static");

            }
            if (unitStage.isSelected())
            {

                this.pipeline.setStage("unit");

            }
            if (integrationStage.isSelected())
            {

                this.pipeline.setStage("integration");

            }

            this.createJson();
            System.exit(0);

        });
        this.add(finish);

    }

    private void createJson()
    {

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().serializeNulls();
        Gson gson = builder.create();
        System.out.println(gson.toJson(this.pipeline));

        try{
            PrintWriter writer = new PrintWriter("test.json", "UTF-8");
            writer.println(gson.toJson(this.pipeline));
            writer.close();
        } catch (IOException e) {
            // do something
        }

    }

}
