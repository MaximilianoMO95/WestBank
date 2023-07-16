package westbank.negocio;

public class Client {
        public int id;
        public String run;
        public String dv;
        public String clave;
        public String name;
        public String ap_paterno;
        public String ap_materno;
        public String address;
        public String comuna;
        public String tel;
        private Account account;

        public Client(String run, String dv, String clave, String name, String ap_paterno, String ap_materno, String address, String comuna, String tel, Account account) {
                this.run        = run;
                this.dv         = dv;
                this.clave      = clave;
                this.name       = name;
                this.ap_paterno = ap_paterno;
                this.ap_materno = ap_materno;
                this.address    = address;
                this.comuna     = comuna;
                this.tel        = tel;
                this.account    = account;
        }

        public Client(int id, String run, String dv, String clave, String name, String ap_paterno, String ap_materno, String address, String comuna, String tel, Account account) {
                this.id         = id;
                this.run        = run;
                this.dv         = dv;
                this.clave      = clave;
                this.name       = name;
                this.ap_paterno = ap_paterno;
                this.ap_materno = ap_materno;
                this.address    = address;
                this.comuna     = comuna;
                this.tel        = tel;
                this.account    = account;
        }

        public Account getAccount() {
                return  this.account;
        }
        public String getName() {
        return name;
    }
}
