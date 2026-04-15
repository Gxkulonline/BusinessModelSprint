package com.sprint.project.business_management_system.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sprint.project.business_management_system.Entity.Office;
import com.sprint.project.business_management_system.repository.OfficeRepository;
@Service
public class OfficeService {
    @Autowired
    private OfficeRepository repo;
    public List<Office> getAll() {
        return repo.findAll();
    }
    public Office getById(String id) {
        return repo.findById(id).orElse(null);
    }
    public Office save(Office o) {
        return repo.save(o);
    }
    public void delete(String id) {
        repo.deleteById(id);
    }
}
