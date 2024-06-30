package miu.waa.project1.service.impl;

import lombok.RequiredArgsConstructor;
import miu.waa.project1.model.Interest;
import miu.waa.project1.model.User;
import miu.waa.project1.repository.InterestRepository;
import miu.waa.project1.service.InterestService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterestServiceImpl implements InterestService {
    private final InterestRepository interestRepository;

    @Override
    public Interest getInterestById(Long id) {
        return interestRepository.findById(id).orElse(null);
    }

    @Override
    public Interest createInterest(Interest interest) {
        return interestRepository.save(interest);
    }

    @Override
    public Interest updateInterest(Long id, Interest interest) {
        Interest existInterest = interestRepository.findById(id).orElse(null);
        if (existInterest == null) return null;
        existInterest.setName(interest.getName());
        return interestRepository.save(existInterest);
    }

    @Override
    public void deleteInterest(Long id) {
        interestRepository.deleteById(id);
    }

    @Override
    public Interest createInterestByUser(Interest interest, User user) {
        interest.setUser(user);
        return interestRepository.save(interest);
    }
}
