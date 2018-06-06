package pl.coderslab.sports_betting.Service.General.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Message;
import pl.coderslab.sports_betting.Repository.MessageRepository;
import pl.coderslab.sports_betting.Service.General.Service.MessageService;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    public void saveMessage(Message message){
        messageRepository.save(message);
    }

    public List<Message> getAllSendMessages(Long id){
        return messageRepository.findAllBySenderId(id);
    }

    public List<Message> getAllReceivedMessages(Long id){
        return messageRepository.findAllByReceiverId(id);
    }
}
