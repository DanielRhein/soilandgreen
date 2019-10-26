import java.util.List;

import de.danielrhein.DocumentReadingListener;
import de.danielrhein.PlantsDTO;
import org.junit.Assert;

public class DocumentReadingListenerUtil implements DocumentReadingListener {

    @Override
    public void documentReaded(List<PlantsDTO> dtoList) {
        Assert.assertNotNull(dtoList);
        Assert.assertEquals(1,dtoList.size());
        Assert.assertEquals("Endivie",dtoList.get(0).getPlant_name());
        Assert.assertEquals("Artischocke",dtoList.get(1).getPlant_name());
    }
}
