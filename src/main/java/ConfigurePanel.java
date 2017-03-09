import javax.swing.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.awt.*;

/**
 * Created by natha on 2/12/2017.
 */
public class ConfigurePanel extends JPanel
{

    private JFrame frame;
    private Pipeline pipeline;

    public ConfigurePanel(JFrame frame)
    {

        this.pipeline = new Pipeline();
        this.frame = frame;
        this.addOptions();

    }

    private void addOptions()
    {

        DefaultComboBoxModel languages = new DefaultComboBoxModel();
        languages.addElement("Select a Language");
        languages.addElement("Java");
        languages.addElement("PHP");

        JComboBox languageSelector = new JComboBox(languages);
        languageSelector.setPreferredSize(new Dimension(150, 25));
        languageSelector.setSelectedIndex(0);

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
        next.setEnabled(false);
        next.addActionListener(e ->
        {

            this.createJson(languageSelector.getItemAt(languageSelector.getSelectedIndex()).toString());
            this.frame.setContentPane(new StagePanel(this.frame, pipeline));
            this.frame.revalidate();

        });
        this.add(next);

        JButton finish = new JButton("finish");
        finish.setEnabled(false);
        this.add(finish);

        languageSelector.addActionListener(e ->
        {

            if (languageSelector.getSelectedIndex() != 0)
            {

                next.setEnabled(true);

            }
            else next.setEnabled(false);

        });

        this.add(languageSelector);

    }

    private void createJson(String language)
    {

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().serializeNulls();
        Gson gson = builder.create();
        this.pipeline.setLanguage(language.toLowerCase());
        System.out.println(gson.toJson(this.pipeline));

    }

}
