package projetsi;


// Utilisation de la classe Message :
// Message m = new Message("type","val");
// String json = Message.toJson(m);
// Message m2 = Message.fromJson(json);

public class Message {

        private String type = null;
        private String mess = null;

        public Message () {
        }
       
        public Message(String type, String mess) {
                this.type = type;
                this.mess = mess;
        }

        public static String toJson(Message m) {
                return "{\"type\":\""+m.type+"\":\"mess\":\""+m.mess+"\"}";
        }
       
        public static Message fromJson(String s) {
               
                try {
                        String re1 = "type\":\"";
                        int pos1 = s.indexOf(re1);
                        int pos2 = s.indexOf("\"",pos1+re1.length());
                       
                        String re2 = "mess\":\"";
                        int pos3 = s.indexOf(re2);
                        int pos4 = s.indexOf("\"",pos3+re2.length());
                       
                        String s1 = s.substring(pos1+re1.length(), pos2);
                        String s2 = s.substring(pos3+re2.length(), pos4);
                       
                       
                        System.out.println("fromJson "+s1+" "+s2);
                        return new Message(s1, s2);
                       
                       
                } catch (Exception e) {
                       
                        return new Message("error","-1");
                }
        }
       

        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
        }

        public String getMess() {
                return mess;
        }

        public void setMess(String mess) {
                this.mess = mess;
        }
}
