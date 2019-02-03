package pl.coderslab.sports_betting.Service.General.Service;

import pl.coderslab.sports_betting.Entity.Message;

import java.util.List;

public interface MessageService {
     void saveMessage(Message message);
     List<Message> getAllSendMessages(Long id);
     List<Message> getAllReceivedMessages(Long id);
}
