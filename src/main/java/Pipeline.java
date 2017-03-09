import java.util.ArrayList;
import java.util.List;

/**
 * Created by natha on 2/12/2017.
 */
public class Pipeline
{

    private String language;
    private List<String> stages = new ArrayList<>();

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public List<String> getStages()
    {
        return stages;
    }

    public void setStage(String stage)
    {
        this.stages.add(stage);
    }
}
