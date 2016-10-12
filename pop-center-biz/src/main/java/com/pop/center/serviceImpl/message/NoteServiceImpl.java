package com.pop.center.serviceImpl.message;

import com.pop.center.dao.message.MessageNotesDAO;
import com.pop.center.dto.message.NoteDto;
import com.pop.center.entity.message.NoteEntity;
import com.pop.center.service.message.NoteService;
import com.pop.mybatis.entity.Page;
import com.pop.mybatis.entity.Pageable;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xugang on 16/10/12.
 */
@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private MessageNotesDAO messageNotesDAO;

    @Override
    public void addNote(NoteDto noteDto) {
        NoteEntity noteEntity = new NoteEntity();
        BeanUtils.copyProperties(noteDto, noteEntity);
        messageNotesDAO.save(noteEntity);
    }

    @Override
    public Page<NoteDto> getNoteBySendId(Long sendId, int sendState, Pageable pageable) {
        Page<NoteEntity> noteEntityPage = messageNotesDAO.getNoteBySendIdAndState(sendId, sendState, pageable);
        return getDtoPage(noteEntityPage);
    }

    @Override
    public Page<NoteDto> getNoteByReceiveId(Long receiveId, int receiveState, Pageable pageable) {
        Page<NoteEntity> noteEntityPage = messageNotesDAO.getNoteByReceiveIdAndState(receiveId, receiveState, pageable);
        return getDtoPage(noteEntityPage);
    }

    @Override
    public void updateSendState(int sendState, int id) {
        messageNotesDAO.updateSendState(sendState, id);
    }

    @Override
    public void updateReceiveState(int receiveState, int id) {
        messageNotesDAO.updateReceiveState(receiveState, id);
    }


    private Page<NoteDto> getDtoPage(Page<NoteEntity> entityPage) {
        Page<NoteDto> noteDtoPage = new Page<>();
        List<NoteEntity> noteEntityList = entityPage.getContent();
        if (!CollectionUtils.isEmpty(noteEntityList)) {
            ArrayList<NoteDto> noteDtoArrayList = new ArrayList<>();
            for (NoteEntity noteEntity : noteEntityList) {
                NoteDto noteDto = new NoteDto();
                BeanUtils.copyProperties(noteEntity, noteDto);
                noteDtoArrayList.add(noteDto);
            }
            noteDtoPage.setContent(noteDtoArrayList);
        }
        return noteDtoPage;
    }


}
