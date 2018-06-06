package pl.coderslab.sports_betting.Service.General.Service;

import pl.coderslab.sports_betting.Entity.Message;

import java.util.List;

public interface MessageService {

    public void saveMessage(Message message);

    public List<Message> getAllSendMessages(Long id);

    public List<Message> getAllReceivedMessages(Long id);

    }
