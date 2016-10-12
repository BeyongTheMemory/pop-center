package com.pop.center.dao.message;

import com.pop.center.entity.message.NoteEntity;
import com.pop.mybatis.entity.Page;
import com.pop.mybatis.entity.Pageable;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by xugang on 16/10/12.
 */
@Repository
public interface MessageNotesDAO {
    void save(NoteEntity noteEntity);

    Page<NoteEntity> getNoteBySendIdAndState(@Param("sendId") long sendId, @Param("sendState") int sendState, Pageable pageable);

    Page<NoteEntity> getNoteByReceiveIdAndState(@Param("receiveId") long receiveId, @Param("receiveState") int receiveState, Pageable pageable);

    void updateSendState(@Param("sendState")int sendState,@Param("id")int id);

    void updateReceiveState(@Param("receiveState")int receiveState,@Param("id")int id);


}
