package ChariO.GiBoo.service;

import ChariO.GiBoo.domain.User;
import ChariO.GiBoo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    //private final User user;

    //@Transactional
    //public User userSave(user) {return userRepository.save(user);}

    @Transactional(readOnly = true)
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findOne(Long userid) { return userRepository.findOne(userid); }

    /**TEST용**/
    @Transactional(readOnly = true)
    public List<User> findOne1(Long userid, int offset, int limit) { return userRepository.findOne1(userid, offset, limit); }

    //@Transactional(readOnly = true)
    //public void delete(Long userid) { return userRepository.deleteById(userid)};
    //public post
    //public save?
}
