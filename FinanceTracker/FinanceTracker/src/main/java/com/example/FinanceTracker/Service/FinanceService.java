package com.example.FinanceTracker.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FinanceTracker.Models.TrackerModel;
import com.example.FinanceTracker.Repository.TrackerRepository;

@Service
public class FinanceService {

    @Autowired
    private TrackerRepository repo;

    public TrackerModel add(TrackerModel data) {
        return repo.save(data);
    }

    public List<TrackerModel> all() {
        return repo.findAll();
    }

    public TrackerModel getOrThrow(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Record ID: " + id));
    }

    public TrackerModel update(Integer id, TrackerModel newData) {
        TrackerModel old = getOrThrow(id);

        old.setDate(newData.getDate());
        old.setExpense(newData.getExpense());
        old.setBudget(newData.getBudget());

        return repo.save(old);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
