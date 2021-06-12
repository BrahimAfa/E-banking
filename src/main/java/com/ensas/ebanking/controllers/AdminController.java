package com.ensas.ebanking.controllers;

// import com.ensas.ebanking.entities.*;
import com.ensas.ebanking.repositories.UserRepository;
// import com.ensas.ebanking.services.EmailService;
// import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

// import static com.ensas.ebanking.enumeration.Role.ROLE_AGENT;

@Controller
@RequestMapping("/")
public class AdminController {
    /*
    private BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();;
    private EmailService emailService=new EmailService();

    @Autowired
    private AgenceRepository agenceRepository;
    @Autowired
    private AdresseRepository adresseRepository;
    @Autowired
    private BanqueRepository banqueRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AdminRepository adminRepository;
    */


    //login
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String loginTo(){
        return "toClient";
    }
    /*
    @GetMapping("/Admin/loginError")
    public String loginerror(String username,String mdp,Model model,HttpSession session){

        model.addAttribute("error","Veuillez vous connectez tout d'abord");
        return "Admin/login";
    }
    @GetMapping("/Admin/loginwitherrors")
    public String invalidelogin(Model model){

        model.addAttribute("error","Username ou mot de passe invalide");
        return "Admin/login";
    }

    @PostMapping("/Admin/loginadmin")
    public String loginPage(HttpSession session,String username,String mdp,Model model){
        Admin admin=adminRepository.findAll().get(0);
        System.out.println("password 1"+admin.getPassword());
        if(admin.getUsername().equals(username) && admin.getPassword().equals(mdp)){
            session.setAttribute("adminId",admin.getId());
            return "redirect:/Admin/index";
        }
        else{
            return "redirect:/Admin/loginwitherrors";
        }


    }
    @GetMapping("/Admin/logout")
    public String logout(HttpSession session){
        session.setAttribute("adminId",null);

        return "Admin/login";
    }

    @GetMapping("/Admin/index")
    public String afficher(Model model,HttpSession session) {
        if(session.getAttribute("adminId")==null){

            return "redirect:/Admin/loginError";
        }
        else{
            double solde=banqueRepository.findById(1L).get().getSolde();
            int agents=agentRepository.findByIsActive(true).size();
            int agencesInactif=agenceRepository.findByActive(false).size();
            int agences=agenceRepository.findByActive(true).size();
            int clients=clientRepository.findAll().size();
            model.addAttribute("solde",solde);
            model.addAttribute("agents",agents);
            model.addAttribute("agences",agences);
            model.addAttribute("clients",clients);
            model.addAttribute("agencesInactives",agencesInactif);
            Admin admin=adminRepository.findAll().get(0);
            model.addAttribute("admin",admin);
            return "Admin/index";
        }


    }
   //Agences
    @GetMapping("/Admin/agences")
    public String afficherAgences(Model model,HttpSession session) {
        if(session.getAttribute("adminId")==null){

            return "redirect:/Admin/loginError";
        }
        List<Agence> agences = agenceRepository.findAll();
        model.addAttribute("agences", agences);
        Admin admin=adminRepository.findAll().get(0);
        model.addAttribute("admin",admin);
        return "Admin/Agence";

    }

    @GetMapping("/Admin/addagence")
    public String addAgences(Model model,HttpSession session) {
        if(session.getAttribute("adminId")==null){

            return "redirect:/Admin/loginError";
        }
        Admin admin=adminRepository.findAll().get(0);
        model.addAttribute("admin",admin);
        return "Admin/ajoutAgence";

    }

    @PostMapping("/Admin/Agence/Add")
    public String InsertAgences(Model model, Long id, String nom,String horaire_debut, String horaire_fin,
                                String num_tele, String ville, String rue, String code_postal,String active) {

        Adresse adresse = new Adresse();
        adresse.setCode_postal(code_postal);
        adresse.setPays("Maroc");
        adresse.setRue(rue);
        adresse.setVille(ville);
        adresseRepository.save(adresse);
        Banque banque = banqueRepository.findById(1L).get();

        Agence agence = new Agence();
        if (id != null) {
            Agence age=agenceRepository.findById(id).get();
            agence.setCode(age.getCode());
            if(active.equals("active")){
                agence.setActive(true);
            }
            else if(active.equals("inactive")){
                agence.setActive(false);
            }
            agence.setId(id);
        }
        if(id==null){
            agence.setActive(true);
            agence.setCode(generateCode());
        }
        agence.setAdresse(adresse);

        agence.setNom(nom);
        agence.setNum_tele(num_tele);
        agence.setHoraire_debut(horaire_debut);
        agence.setHoraire_fin(horaire_fin);
        agence.setBanque(banque);

        agenceRepository.save(agence);

        return "redirect:/Admin/agences";

    }

    @GetMapping("/Admin/modifieragence")
    public String update(Model model, Long id,HttpSession session) {
        if(session.getAttribute("adminId")==null){

            return "redirect:/Admin/loginError";
        }
        Agence agence=agenceRepository.findById(id).get();
        model.addAttribute("agence",agence);
        Admin admin=adminRepository.findAll().get(0);
        model.addAttribute("admin",admin);
        return "Admin/modifierAgence";
    }

    @GetMapping("/Admin/deleteagence")
    public String deleteagence(Model model, Long id) {

        Agence agence = agenceRepository.findById(id).get();
        agence.setActive(false);
        agenceRepository.save(agence);
         Agent agent = agence.getAgent();
        //adresseRepository.delete(agence.getAdresse());
       if (agent != null) {
           agent.setActive(false);
            agentRepository.save(agent);
        }
       Set<Client> clients = agence.getClients();
        if (clients != null) {
            for (Client client : clients) {
                client.setActive(false);
               clientRepository.save(client);
            }
        }


            return "redirect:/Admin/agences";

    }
    //Agents

    @GetMapping("/Admin/agents")
    public String afficherAgents(Model model,HttpSession session) {
        if(session.getAttribute("adminId")==null){

            return "redirect:/Admin/loginError";
        }
        List<Agent> agets = agentRepository.findAll();
      model.addAttribute("agents", agets);
        Admin admin=adminRepository.findAll().get(0);
        model.addAttribute("admin",admin);
        return "Admin/agents";

    }
    @GetMapping("/Admin/addAgent")
    public String addAgents(Model model,HttpSession session) {
        if(session.getAttribute("adminId")==null){

            return "redirect:/Admin/loginError";
        }
        List<Agence> agencess=agenceRepository.findAll();
        List<Agent> agents=agentRepository.findAll();
        List<Agence> agences=new ArrayList<>();
        for (Agence agence:agencess) {
                Agent agent=agentRepository.findByAgence(agence);
                //System.out.println(agent.getNom());
                 if(agent==null && agence.isActive()){
                     agences.add(agence);
                 }
        }
        model.addAttribute("agences",agences);
        Admin admin=adminRepository.findAll().get(0);
        model.addAttribute("admin",admin);
        return "Admin/ajoutAgents";

    }
    @PostMapping("/Admin/add/Agent")
    public String insereragents(Long id,String cin, String nom, String prenom, String email, String date_naissance,
                                String num_tele,String agence,String active) throws ParseException, MessagingException {
        Agent agent=new Agent() ;
       // System.out.println("iddddd"+id);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        agent.setDate_naissance(df.parse(date_naissance));
        String password = generatePassword();
        if(id!=null){
            agent.setId(id);
            Agent agent1=agentRepository.findById(id).get();
           agent.setPassword(agent1.getPassword());
           agent.setUsername(agent1.getUsername());
           agent.setJoinDate(agent1.getJoinDate());
           agent.setCode_agent(agent1.getCode_agent());
            if(active.equals("active")){
                agent.setActive(true);
            }
            else{
                agent.setActive(false);
            }

        }
        else {
            String username=generateUsername();
            agent.setUsername(username);
            agent.setPassword(encodePassword(password));
            agent.setActive(true);
            agent.setJoinDate(new Date());
            agent.setCode_agent(generateCode());
            emailService.sendNewPasswordEmail(nom+' '+prenom,username,password,email);

            //System.out.println("agent username: " + username + " password: " + password);
        }
        agent.setCin(cin);
        agent.setNom(nom);
        agent.setPrenom(prenom);
        agent.setEmail(email);
        agent.setLastLoginDate(new Date());
        agent.setLastLoginDateDisplay(new Date());
        System.out.println(password);

        agent.setNotLocked(true);
        agent.setRoles(ROLE_AGENT.name());
        agent.setAuthorities(ROLE_AGENT.getAuthorities());
        agent.setNum_tele(num_tele);
        List<Agence> agen=agenceRepository.findByNom(agence);
        agent.setAgence(agen.get(0));
        userRepository.save(agent);
        return "redirect:/Admin/agents";

    }
    @GetMapping("/Admin/deleteagent")
    public String deleteagent(Model model, Long id) {
        Agent agent=agentRepository.findById(id).get();
        agent.setActive(false);
        agentRepository.save(agent);
        return "redirect:/Admin/agents";

    }
    @GetMapping("/Admin/updateagent")
    public String updateagent(Model model,Long id,HttpSession session) {
        if(session.getAttribute("adminId")==null){

            return "redirect:/Admin/loginError";
        }
        List<Agence> agencess=agenceRepository.findAll();
        List<Agent> agents=agentRepository.findAll();
        List<Agence> agences=new ArrayList<Agence>();
        for (Agence agence:agencess) {
            Agent agent=agentRepository.findByAgence(agence);
            //System.out.println(agent.getNom());
            if(agent==null && agence.isActive()){
                agences.add(agence);
            }
        }
        model.addAttribute("agences",agences);
        Agent agent=agentRepository.findById(id).get();
        model.addAttribute("agent",agent);
        Admin admin=adminRepository.findAll().get(0);
        model.addAttribute("admin",admin);
        return "Admin/edit_agent";
    }


    //clients
    @GetMapping("/Admin/clients")
    public String afficherclient(Model model,HttpSession session){
        if(session.getAttribute("adminId")==null){

            return "redirect:/Admin/loginError";
        }
        List<Client> clients=clientRepository.findAll();
        model.addAttribute("clients",clients);
        Admin admin=adminRepository.findAll().get(0);
        model.addAttribute("admin",admin);
        return "Admin/clients";
    }
    //Profile
    @GetMapping("/Admin/profile")
    public String afficherprofile(Model model,HttpSession session){
        if(session.getAttribute("adminId")==null){

            return "redirect:/Admin/loginError";
        }
        Admin admin=adminRepository.findAll().get(0);
        model.addAttribute("admin",admin);
        return "Admin/MonProfile";
    }


//encoder passwoord /generate username
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(8);
    }
    private String generateUsername() {
        return RandomStringUtils.randomAlphanumeric(6);
    }
    private String generateCode() {
        return RandomStringUtils.randomAlphanumeric(7);
    }*/

    }



