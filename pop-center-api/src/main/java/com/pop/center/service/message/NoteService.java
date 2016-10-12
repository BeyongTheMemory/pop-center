package com.pop.center.service.message;

import com.pop.center.dto.message.NoteDto;
import com.pop.mybatis.entity.Page;
import com.pop.mybatis.entity.Pageable;

/**
 * Created by xugang on 16/8/6.
 */
public interface NoteService {
    void addNote(NoteDto noteDto);

    Page<NoteDto> getNoteBySendId(Long sendId, int sendState, Pageable pageable);

    Page<NoteDto> getNoteByReceiveId(Long receiveId, int receiveState, Pageable pageable);

    void updateSendState(int sendState, int id);

    void updateReceiveState(int receiveState, int id);

}
