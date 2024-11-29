import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Message {
    private String sender;
    private String content;

    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    @Override
    public String toString() {
        return sender + ": " + content;
    }
}

class ChatRoom {
    private String name;
    private List<Message> messages;

    public ChatRoom(String name) {
        this.name = name;
        this.messages = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addMessage(String sender, String content) {
        messages.add(new Message(sender, content));
    }

    public void displayMessages() {
        System.out.println("\nChat Room: " + name);
        if (messages.isEmpty()) {
            System.out.println("No messages yet.");
        } else {
            for (Message message : messages) {
                System.out.println(message);
            }
        }
    }
}

class ChatApplication {
    private HashMap<String, ChatRoom> chatRooms;

    public ChatApplication() {
        chatRooms = new HashMap<>();
    }

    public void createChatRoom(String name) {
        if (chatRooms.containsKey(name)) {
            System.out.println("Chat room already exists.");
        } else {
            chatRooms.put(name, new ChatRoom(name));
            System.out.println("Chat room \"" + name + "\" created successfully.");
        }
    }

    public ChatRoom joinChatRoom(String name) {
        return chatRooms.get(name);
    }

    public void listChatRooms() {
        System.out.println("\nAvailable Chat Rooms:");
        if (chatRooms.isEmpty()) {
            System.out.println("No chat rooms available.");
        } else {
            for (String name : chatRooms.keySet()) {
                System.out.println(name);
            }
        }
    }
}

public class SimpleChatApplication {
    public static void main(String[] args) {
        ChatApplication app = new ChatApplication();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChat Application");
            System.out.println("1. Create Chat Room");
            System.out.println("2. List Chat Rooms");
            System.out.println("3. Join Chat Room");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter chat room name: ");
                    String roomName = scanner.nextLine();
                    app.createChatRoom(roomName);
                    break;

                case 2:
                    app.listChatRooms();
                    break;

                case 3:
                    System.out.print("Enter chat room name to join: ");
                    String joinRoomName = scanner.nextLine();
                    ChatRoom room = app.joinChatRoom(joinRoomName);
                    if (room != null) {
                        while (true) {
                            System.out.println("\nChat Room: " + room.getName());
                            System.out.println("1. Send Message");
                            System.out.println("2. View Messages");
                            System.out.println("3. Leave Chat Room");
                            System.out.print("Enter your choice: ");
                            int chatChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            if (chatChoice == 1) {
                                System.out.print("Enter your name: ");
                                String sender = scanner.nextLine();
                                System.out.print("Enter your message: ");
                                String content = scanner.nextLine();
                                room.addMessage(sender, content);
                            } else if (chatChoice == 2) {
                                room.displayMessages();
                            } else if (chatChoice == 3) {
                                System.out.println("Leaving chat room...");
                                break;
                            } else {
                                System.out.println("Invalid choice. Try again.");
                            }
                        }
                    } else {
                        System.out.println("Chat room does not exist.");
                    }
                    break;

                case 4:
                    System.out.println("Exiting application.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
