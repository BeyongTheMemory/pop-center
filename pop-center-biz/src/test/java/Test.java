import com.pop.center.dao.message.MessageNotesDAO;
import com.pop.center.dto.message.NoteDto;
import com.pop.center.service.message.NoteService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by xugang on 16/10/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/applicationContext.xml"})
public class Test {
    @Autowired
    private MessageNotesDAO messageNotesDAO;
    @Autowired
    private NoteService noteService;
    @org.junit.Test
    public void test(){
        NoteDto noteEntity = new NoteDto();
        noteEntity.setMessage("xxx");
        noteEntity.setSendId(1);
        noteEntity.setReceiveId(1);
        noteEntity.setSendName("a");
        noteEntity.setSendSex(1);
        noteService.addNote(noteEntity);
    }
}
