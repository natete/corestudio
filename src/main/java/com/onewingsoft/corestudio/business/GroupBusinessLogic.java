package com.onewingsoft.corestudio.business;

import com.onewingsoft.corestudio.model.Group;
import com.onewingsoft.corestudio.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

/**
 * @author Ignacio González Bullón - <nacho.gonzalez.bullon@gmail.com>
 * @since 11/12/15.
 */
@Service
public class GroupBusinessLogic extends BaseBusinessLogic<Group>{

    @Autowired
    private GroupRepository groupRepository;

    @Override
    protected void validateEntity(Group entity) throws IllegalArgumentException {
        if(entity.getDays() == null || entity.getDays().isEmpty()) {
            throw new IllegalArgumentException("Un grupo debe tener unas fechas asociadas");
        }
        if(entity.getHour() == null || entity.getHour() < 7 || entity.getHour() > 22) {
            throw new IllegalArgumentException("La fecha no está presente o tiene un valor inválido");
        }
        if(entity.getActivity() == null) {
            throw new IllegalArgumentException("Un grupo debe tener una actividad asociada");
        }
        if(entity.getLevel() == null) {
            throw new IllegalArgumentException("Un grupo debe tener un nivel");
        }
    }

    @Override
    protected Group processEntity(Group group) {
        return group;
    }

    public Iterable<Group> getByActivity(Long activityId) {
        return groupRepository.findByActivityId(activityId);
    }

    @Override
    protected PagingAndSortingRepository getRepository() {
        return this.groupRepository;
    }
}