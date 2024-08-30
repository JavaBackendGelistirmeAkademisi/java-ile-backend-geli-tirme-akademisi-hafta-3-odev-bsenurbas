import java.util.*;

public class socialMediaPlatform {
    public static void main(String[] args) {
        // Kullanıcıların oluşturulması
        User alice = new User("Alice");
        User bob = new User("Bob");
        User charlie = new User("Charlie");

        // Kullanıcıların birbirlerini takip etmesi
        alice.follow(bob);      // Alice, Bob'u takip ediyor
        bob.follow(charlie);    // Bob, Charlie'yi takip ediyor
        charlie.follow(alice);  // Charlie, Alice'i takip ediyor

        // Kullanıcıların gönderi oluşturması
        bob.createPost("Merhaba Dünya!");                 // Bob'un ilk gönderisi
        charlie.createPost("Şehri keşfediyorum!");        // Charlie'nin gönderisi
        alice.createPost("Java öğrenmek eğlenceli!");     // Alice'in gönderisi

        // Kullanıcıların birbirlerinin gönderilerine yorum yapması
        alice.addCommentToPost(bob, 0, "Güzel gönderi!");                  // Alice, Bob'un gönderisine yorum yapıyor
        charlie.addCommentToPost(bob, 0, "Katılıyorum, harika bir gönderi!"); // Charlie, Bob'un gönderisine yorum yapıyor
        bob.addCommentToPost(charlie, 1, "Şehir harika görünüyor!");       // Bob, Charlie'nin gönderisine yorum yapıyor

        // Kullanıcıların beğendikleri gönderileri favorilere eklemesi
        alice.addToPostFavorites(bob, 0);     // Alice, Bob'un gönderisini favorilere ekliyor
        charlie.addToPostFavorites(alice, 2); // Charlie, Alice'in gönderisini favorilere ekliyor

        // Kullanıcıların takip ettikleri kişilerin gönderilerini görüntülemesi
        System.out.println("\n--- Alice'in Ana Sayfası ---");
        alice.showFeed();  // Alice'in takip ettiği kişilerin gönderilerini göster

        System.out.println("\n--- Bob'un Ana Sayfası ---");
        bob.showFeed();    // Bob'un takip ettiği kişilerin gönderilerini göster

        System.out.println("\n--- Charlie'nin Ana Sayfası ---");
        charlie.showFeed();  // Charlie'nin takip ettiği kişilerin gönderilerini göster

        // Tüm kullanıcıların kendi gönderilerini ve favorilerini görüntülemesi
        System.out.println("\n--- Alice'in Gönderileri ---");
        alice.showPosts();

        System.out.println("\n--- Bob'un Gönderileri ---");
        bob.showPosts();

        System.out.println("\n--- Charlie'nin Gönderileri ---");
        charlie.showPosts();

        System.out.println("\n--- Alice'in Favorileri ---");
        alice.showFavorites();

        System.out.println("\n--- Bob'un Favorileri ---");
        bob.showFavorites();

        System.out.println("\n--- Charlie'nin Favorileri ---");
        charlie.showFavorites();

    }

    static class User{
        private String name;
        private LinkedHashMap<Integer, Post> posts; // Kullanıcının gönderileri
        private HashSet<User> following; // Kullanıcının takip ettiği kişiler
        private TreeSet<Post> favorites; // Kullanıcının beğendiği gönderiler
        private ArrayList<Post> feed; // Kullanıcının takip ettiği kişilerin gönderileri
        private HashMap<Integer, Comment> allComments; // Tüm yorumlar için bir HashMap
        private static int postCounter = 0; // Gönderi sayacı

        public User (String name){
            this.name = name;
            this.posts = new LinkedHashMap<>();
            this.following = new HashSet<>();
            this.favorites = new TreeSet<>();
            this.feed = new ArrayList<>();
            this.allComments = new HashMap<>();
        }

        public void follow(User user){
            following.add(user);
            System.out.println(name + ", " + user.getName() + " kullanıcısını takip ediyor.");
        }

        public void createPost (String content){
            Post newPost = new Post(postCounter++, this, content);
            posts.put(newPost.getId(), newPost);
            System.out.println(name + ", yeni bir gönderi oluşturdu: " + content);
            for (User follower : following) {
                follower.addToFeed(newPost);  // Takipçilerin feed'ine gönderiyi ekle
            }
        }

        public void addCommentToPost (User user, int postId, String comment){
            Post post = user.getPost(postId);
            if (post != null){
                Comment newComment = new Comment(this, comment);
                post.addComment(newComment);
                allComments.put(allComments.size(), newComment); // Yorumu HashMap'e ekle
                System.out.println(name + ", " + user.getName() + " kullanıcısının gönderisine yorum yaptı: " + comment);
            }
        }

        public void addToPostFavorites (User user, int postId){
            Post post = user.getPost(postId);
            if (post != null){
                favorites.add(post);
                System.out.println(name + ", " + user.getName() + " kullanıcısının gönderisini beğendi: " + post.getContent());
            }
        }

        public void showFeed(){
            System.out.println("\n" + name + " kullanıcısının Ana Sayfası");
            Iterator<Post> iterator = feed.iterator();
            while (iterator.hasNext()) {
                Post post = iterator.next();
                System.out.println(name + " kullanıcısının takip ettiği bir gönderi: " + post.getContent());
            }
        }

        public void addToFeed(Post post) {
            feed.add(post);
        }

        public Post getPost(int postId){
            return posts.get(postId);
        }

        public void showPosts(){
            for(Post post : posts.values()){
                System.out.println(name + " kullanıcısının gönderisi: " + post.getContent());
                post.showComments();
            }
        }

        public void showFavorites(){
            System.out.println(name + " kullanıcısının favori gönderileri: ");
            if (favorites.isEmpty()){
                System.out.println("Henüz favori gönderi yok.");
            }else {
                Iterator<Post> iterator = favorites.iterator();
                while (iterator.hasNext()) {
                    Post post = iterator.next();
                    System.out.println("Favori gönderi: " + post.getContent());
                }
            }
        }

        public class Post implements Comparable<Post> {
            private int id;
            private User user;
            private String content;
            private LinkedList<Comment> comments;

            public Post(int id, User user, String content){
                this.id = id;
                this.user = user;
                this.content = content;
                this.comments = new LinkedList<>();
            }

            public int getId(){
                return id;
            }

            public String getContent(){
                return content;
            }

            public void addComment(Comment comment){
                comments.add(comment);
            }

            public void showComments(){
                System.out.println("Gönderiye yapılan yorumlar: ");
                Iterator<Comment> iterator = comments.iterator();
                while (iterator.hasNext()) {
                    Comment comment = iterator.next();
                    System.out.println(comment.getContent());
                }
            }

            @Override
            public int compareTo(Post other){
                return Integer.compare(this.id, other.id);
            }
        }

        public class Comment{
            private User user;
            private  String content;

            public Comment(User user, String content){
                this.user = user;
                this.content = content;
            }
            public String getContent(){
                return content;
            }
        }
        public String getName(){
            return name;
        }
    }
}
