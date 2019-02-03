package pl.coderslab.sports_betting.Service.General.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Message;
import pl.coderslab.sports_betting.Repository.General.MessageRepository;
import pl.coderslab.sports_betting.Service.General.Service.MessageService;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * Method is saving object message into databse
     * @param message object message
     */
    public void saveMessage(Message message){
        messageRepository.save(message);
    }

    /**
     * Method is looking for all send messages by user Id
     * @param id user Id
     * @return list of all send messages by user
     */
    public List<Message> getAllSendMessages(Long id){
        return messageRepository.findAllBySenderId(id);
    }
    /**
     * Method is looking for all received messages by user Id
     * @param id user Id
     * @return list of all received messages by user
     */
    public List<Message> getAllReceivedMessages(Long id){
        return messageRepository.findAllByReceiverId(id);
    }
}
