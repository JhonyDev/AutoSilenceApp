package app.autosilenceapp.AsmaUlHusna;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.os.Bundle;
import app.autosilenceapp.AsmaUlHusna.model.NamesModel;
import app.autosilenceapp.AsmaUlHusna.namesAdapter.NamesAdapterClass;
import app.autosilenceapp.R;

public class NamesofALLAH extends Activity {


    NamesModel[] myListData;


    //public static int imagesSmall[] = {R.drawable.thumba1, R.drawable.thumba2, R.drawable.thumba3, R.drawable.thumba4, R.drawable.thumba5, R.drawable.thumba6, R.drawable.thumba7, R.drawable.thumba8, R.drawable.thumba9, R.drawable.thumba10, R.drawable.thumba11, R.drawable.thumba12, R.drawable.thumba13, R.drawable.thumba14, R.drawable.thumba15, R.drawable.thumba16, R.drawable.thumba17, R.drawable.thumba18, R.drawable.thumba19, R.drawable.thumba20, R.drawable.thumba21, R.drawable.thumba22, R.drawable.thumba23, R.drawable.thumba24, R.drawable.thumba25, R.drawable.thumba26, R.drawable.thumba27, R.drawable.thumba28, R.drawable.thumba29, R.drawable.thumba30, R.drawable.thumba31, R.drawable.thumba32, R.drawable.thumba33, R.drawable.thumba34, R.drawable.thumba35, R.drawable.thumba36, R.drawable.thumba37, R.drawable.thumba38, R.drawable.thumba39, R.drawable.thumba40, R.drawable.thumba41, R.drawable.thumba42, R.drawable.thumba43, R.drawable.thumba44, R.drawable.thumba45, R.drawable.thumba46, R.drawable.thumba47, R.drawable.thumba48, R.drawable.thumba49, R.drawable.thumba50, R.drawable.thumba51, R.drawable.thumba52, R.drawable.thumba53, R.drawable.thumba54, R.drawable.thumba55, R.drawable.thumba56, R.drawable.thumba57, R.drawable.thumba58, R.drawable.thumba59, R.drawable.thumba60, R.drawable.thumba61, R.drawable.thumba62, R.drawable.thumba63, R.drawable.thumba64, R.drawable.thumba65, R.drawable.thumba66, R.drawable.thumba67, R.drawable.thumba68, R.drawable.thumba69, R.drawable.thumba70, R.drawable.thumba71, R.drawable.thumba72, R.drawable.thumba73, R.drawable.thumba74, R.drawable.thumba75, R.drawable.thumba76, R.drawable.thumba77, R.drawable.thumba78, R.drawable.thumba79, R.drawable.thumba80, R.drawable.thumba81, R.drawable.thumba82, R.drawable.thumba83, R.drawable.thumba84, R.drawable.thumba85, R.drawable.thumba86, R.drawable.thumba87, R.drawable.thumba88, R.drawable.thumba89, R.drawable.thumba90, R.drawable.thumba91, R.drawable.thumba92, R.drawable.thumba93, R.drawable.thumba94, R.drawable.thumba95, R.drawable.thumba96, R.drawable.thumba97, R.drawable.thumba98, R.drawable.thumba99};
    public static int imageLarge[] = {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10, R.drawable.a11, R.drawable.a12, R.drawable.a13, R.drawable.a14, R.drawable.a15, R.drawable.a16, R.drawable.a17, R.drawable.a18, R.drawable.a19, R.drawable.a20, R.drawable.a21, R.drawable.a22, R.drawable.a23, R.drawable.a24, R.drawable.a25, R.drawable.a26, R.drawable.a27, R.drawable.a28, R.drawable.a29, R.drawable.a30, R.drawable.a31, R.drawable.a32, R.drawable.a33, R.drawable.a34, R.drawable.a35, R.drawable.a36, R.drawable.a37, R.drawable.a38, R.drawable.a39, R.drawable.a40, R.drawable.a41, R.drawable.a42, R.drawable.a43, R.drawable.a44, R.drawable.a45, R.drawable.a46, R.drawable.a47, R.drawable.a48, R.drawable.a49, R.drawable.a50, R.drawable.a51, R.drawable.a52, R.drawable.a53, R.drawable.a54, R.drawable.a55, R.drawable.a56, R.drawable.a57, R.drawable.a58, R.drawable.a59, R.drawable.a60, R.drawable.a61, R.drawable.a62, R.drawable.a63, R.drawable.a64, R.drawable.a65, R.drawable.a66, R.drawable.a67, R.drawable.a68, R.drawable.a69, R.drawable.a70, R.drawable.a71, R.drawable.a72, R.drawable.a73, R.drawable.a74, R.drawable.a75, R.drawable.a76, R.drawable.a77, R.drawable.a78, R.drawable.a79, R.drawable.a80, R.drawable.a81, R.drawable.a82, R.drawable.a83, R.drawable.a84, R.drawable.a85, R.drawable.a86, R.drawable.a87, R.drawable.a88, R.drawable.a89, R.drawable.a90, R.drawable.a91, R.drawable.a92, R.drawable.a93, R.drawable.a94, R.drawable.a95, R.drawable.a96, R.drawable.a97, R.drawable.a98, R.drawable.a99};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_namesof_a_l_l_a_h);
        myListData  = new NamesModel[]{
                new NamesModel("He who wills goodness and mercy for all His creatures","Ar-Rahman", R.drawable.a1,"The Beneficent"),
                new NamesModel("He who acts with extreme kindness","Ar-Raheem", R.drawable.a2,"The Merciful"),
                new NamesModel("The Sovereign Lord, The One with the complete Dominion, the One Whose Dominion is clear from imperfection","Al-Malik", R.drawable.a3, "The Eternal Lord"),
                new NamesModel("The One who is pure from any imperfection and clear from children and adversaries","Al-Quddus", R.drawable.a4,"The Most Sacred"),
                new NamesModel("The One who is free from every imperfection.","As-Salam",  R.drawable.a5,"The Embodiment of Peace"),
                new NamesModel("The One who witnessed for Himself that no one is God but Him. And He witnessed for His believers that they are truthful in their belief that no one is God but Him",
                        "Al-Mu min", R.drawable.a6,"The Infuser of Faith"),
                new NamesModel("The One who witnesses the saying and deeds of His creatures","Al-Muhaymin", R.drawable.a7,"The Preserver of Safety"),

                new NamesModel("The Strong, The Defeater who is not defeated","Al-Aziz", R.drawable.a8,"The Mighty One"),
                new NamesModel("The One that nothing happens in His Dominion except that which He willed","Al-Jabbar", R.drawable.a9,"The Omnipotent One"),
                new NamesModel("The One who is clear from the attributes of the creatures and from resembling them.","Al-Mutakabbir", R.drawable.a10,"The Dominant One"),
                new NamesModel("The One who brings everything from non-existence to existence",
                "Al-Khaaliq", R.drawable.a11,"The Creator"),
 new NamesModel("The Maker, The Creator who has the Power to turn the entities.","Al-Baari",  R.drawable.a12,"The Evolver"),
 new NamesModel("The One who forms His creatures in different pictures","Al-Musawwir", R.drawable.a13,"The Flawless Shaper"),
 new NamesModel("The One who forms His creatures in different pictures.","Al-Ghaffar", R.drawable.a14, "The Great Forgiver"),
 new NamesModel("The Forgiver, The One who forgives the sins of His slaves time and time again.",  "Al-Qahhaar", R.drawable.a15,"The All-Prevailing One"),
 new NamesModel("The Domnant, The One who has the perfect Power and is not unable over anything.",
                "Al-Wahhaab", R.drawable.a16,"The Supreme Bestower"),
              new NamesModel("The One who is Generous in giving plenty without any return. He is everything that benefits whether Halal or Haram.",
                "Ar-Razzaaq", R.drawable.a17,"The Total Provider"),
 new NamesModel("he Sustainer, The Provider. The Opener, The Reliever, The Judge, The One who opens for His slaves the closed worldly and religious matters.","Al-Fattaah", R.drawable.a18,"The Supreme Solver"),
                new NamesModel("The Knowledgeable; The One nothing is absent from His knowledge","Al- Alim",  R.drawable.a19,"The All-Knowing One"),

                 new NamesModel("The Constrictor, The Withholder, The One who constricts the sustenance by His wisdom and expands and widens it with His Generosity and Mercy.",
                "Al-Qaabid", R.drawable.a20,"The Restricting One"),
 new NamesModel("The Englarger, The One who constricts the sustenance by His wisdom and expands and widens it with His Generosity and Mercy.","Al-Basit", R.drawable.a21," The Extender"),
 new NamesModel("The Abaser, The One who lowers whoever He willed by His Destruction and raises whoever He willed by His Endowment.","Al-Khaafid", R.drawable.a22,"The Reducer"),
                new NamesModel("The Exalter, The Elevator, The One who lowers whoever He willed by His Destruction and raises whoever He willed by His Endowment.","Ar-Raafi", R.drawable.a23," The Elevating One"),

                new NamesModel("He gives esteem to whoever He willed, hence there is no one to degrade Him; And He degrades whoever He willed, hence there is no one to give Him esteem.",
                        "Al-Mu izz", R.drawable.a24,"The Honourer-Bestower"),
                new NamesModel("The Dishonourer, The Humiliator, He gives esteem to whoever He willed, hence there is no one to degrade Him; And He degrades whoever He willed, hence there is no one to give Him esteem.",
                        " Al-Muzil", R.drawable.a25," The Abaser"),

                new NamesModel(" The Hearer, The One who Hears all things that are heard by His Eternal Hearing without an ear, instrument or organ.","As-Sami", R.drawable.a26,"The All-Hearer"),

           new NamesModel("The All-Noticing, The One who Sees all things that are seen by His Eternal Seeing without a pupil or any other instrument.",
                "Al-Basir",  R.drawable.a27,"The All-Seeing"),
 new NamesModel("The Judge, He is the Ruler and His judgment is His Word."," Al-Hakam", R.drawable.a28,"The Impartial Judge"),
 new NamesModel("The Just, The One who is entitled to do what He does.",
                "Al-Adl", R.drawable.a29,"The Embodiment of Justice"),
 new NamesModel("The Subtle One, The Gracious, The One who is kind to His slaves and endows upon them."
                ,"Al-Latif",  R.drawable.a30,"The Knower of Subtleties"),

                new NamesModel("The One who knows the truth of things.The Forebearing","Al-Khabir", R.drawable.a31,"The All-Aware One"),
 new NamesModel("The One who delays the punishment for those who deserve it and then He might forgive them",
                "Al-Halim",  R.drawable.a32,"The Clement One"),
 new NamesModel( "The Great One, The Mighty, The One deserving the attributes of Exaltment, Glory, Extolement, and Purity from all imperfection.","Al- Azim",  R.drawable.a33,"The Magnificent One"),
               new NamesModel("The All-Forgiving, The Forgiving, The One who forgives a lot.","Al-Ghafur", R.drawable.a34,"The Great Forgiver"),
 new NamesModel("The Grateful, The Appreciative, The One who gives a lot of reward for a little obedience.","Ash-Shakur", R.drawable.a35,"The Acknowledging One"),
 new NamesModel("The Most High, The One who is clear from the attributes of the creatures.","Al-Ali", R.drawable.a36,"The Sublime One"),
 new NamesModel("The Most Great, The Great, The One who is greater than everything in status.","Al-Kabir", R.drawable.a37,"The Great One"),
 new NamesModel("The Preserver, The Protector, The One who protects whatever and whoever He willed to protect.","Al-Hafiz",  R.drawable.a38,"The Guarding One"),
 new NamesModel("The Maintainer, The Guardian, The Feeder, The One who has the Power.","Al-Muqit", R.drawable.a39,"The Sustaining One"),
 new NamesModel("The Reckoner, The One who gives the satisfaction."," Al-Hasib", R.drawable.a40,"The Reckoning One"),
 new NamesModel("The Sublime One, The Beneficent, The One who is attributed with greatness of Power and Glory of status.","Al-Jalil",  R.drawable.a41,"The Majestic One"),
 new NamesModel("The Generous One, The Gracious, The One who is attributed with greatness of Power and Glory of status.","Al-Karim", R.drawable.a42,"The Bountiful One"),
 new NamesModel("The Watcher, The One that nothing is absent from Him. Hence it’s meaning is related to the attribute of Knowledge.", "Ar-Raqib", R.drawable.a43,"The Watchful One"),
 new NamesModel("The Responsive, The Hearkener, The One who answers the one in need if he asks Him and rescues the yearner if he calls upon Him.","Al-Mujib", R.drawable.a44,"The Responding One"),
 new NamesModel("The Vast, The All-Embracing, The Knowledgeable.","Al-Wasi",  R.drawable.a45,"The All-Pervading One"),
 new NamesModel("The Wise, The Judge of Judges, The One who is correct in His doings.","Al-Hakim", R.drawable.a46, "The Wise One"),
 new NamesModel("The One who loves His believing slaves and His believing slaves love Him. His love to His slaves is His Will to be merciful to them and praise them","Al-Wadud", R.drawable.a47," The Loving One"),
 new NamesModel("The Most Glorious One, The One who is with perfect Power, High Status, Compassion, Generosity and Kindness.","Al-Majeed",  R.drawable.a48,"The Glorious One"),
 new NamesModel("The Resurrector, The Raiser (from death), The One who resurrects His slaves after death for reward and/or punishment.","Al-Baa ith", R.drawable.a49,"The Infuser of New Life"),
 new NamesModel("The Witness, The One who nothing is absent from Him.The Truth, The True, The One who truly exists.","Ash-Shahid", R.drawable.a50,"The All Observing Witness"),
 new NamesModel("The Trustee, The One who gives the satisfaction and is relied upon.","Al-Haqq", R.drawable.a51, "The Embodiment of Truth"),

 new NamesModel("The Most Strong, The Strong, The One with the complete Power","Al-Wakil", R.drawable.a52,"The Universal Trustee"),
 new NamesModel("The One with extreme Power which is un-interrupted and He does not get tired.","Al-Qawiyy", R.drawable.a53,"The Strong One"),
 new NamesModel("The Protecting Friend, The Supporter.","Al-Matin", R.drawable.a54, "The Firm One"),
new NamesModel("The Praiseworthy, The praised One who deserves to be praised.","Al-Wali",  R.drawable.a55,"The Protecting Associate"),
 new NamesModel("The Counter, The Reckoner, The One who the count of things are known to him.","Al-Hamid", R.drawable.a56,"The Sole-Laudable One"),
 new NamesModel("The One who started the human being. That is, He created him.","Al-Muhsi", R.drawable.a57,"The All-Enumerating One"),
 new NamesModel("The Reproducer, The One who brings back the creatures after death","Al-Mubdi", R.drawable.a58,"The Originator"),
 new NamesModel("The Restorer, The Giver of Life, The One who took out a living human from semen that does not have a soul. He gives life by giving the souls back to the worn out bodies on the resurrection day and He makes the hearts alive by the light of knowledge.",
                "Al-Mu id", R.drawable.a59,"The Restorer"),
new NamesModel("The Creator of Death, The Destroyer, The One who renders the living dead.",
                "Al-Muhyi",  R.drawable.a60,"The Maintainer of life"),
new  NamesModel( "The Alive, The One attributed with a life that is unlike our life and is not that of a combination of soul, flesh or blood.",
                "Al-Mumit",  R.drawable.a61," The Inflictor of Death"),
                 new NamesModel( "The Alive, The One attributed with a life that is unlike our life and is not that of a combination of soul, flesh or blood.",
                "Al-Mumit",  R.drawable.a61," The Inflictor of Death"),
 new NamesModel("The One who remains and does not end.",
                "Al-Hayy", R.drawable.a62," The Eternally Living One"),

                new NamesModel("The Perceiver, The Finder, The Rich who is never poor. Al-Wajd is Richness.",
                "Al-Qayyum",  R.drawable.a63," The Self-Subsisting One"),
 new NamesModel("The Glorious, He who is Most Glorious.","Al-Waajid", R.drawable.a64, "The Pointing One"),
 new NamesModel("The Unique, The One, The One without a partner","Al-Maajid", R.drawable.a65,"The All-Noble One"),
 new NamesModel("The One", "Al-Waahid",  R.drawable.a66,"The Only One"),
 new NamesModel("The Eternal, The Independent, The Master who is relied upon in matters and reverted to in ones needs.","Al-Ahad", R.drawable.a67,"The Sole One"),
 new NamesModel("The Able, The Capable, The One attributed with Power.","As-Samad", R.drawable.a68,"The Supreme Provider"),
 new NamesModel("The Powerful, The Dominant, The One with the perfect Power that nothing is withheld from Him.",  "Al-adir", R.drawable.a69,"The Omnipotent One"),
 new NamesModel("The Expediter, The Promoter, The One who puts things in their right places. He makes ahead what He wills and delays what He wills.",
                "Al-Muqtadir", R.drawable.a70,"The All Authoritative One"),
 new NamesModel( "The Delayer, the Retarder, The One who puts things in their right places. He makes ahead what He wills and delays what He wills.", "Al-Muqaddim", R.drawable.a71, "The Expediting One"),
new NamesModel("The First, The One whose Existence is without a beginning.","Al-Mu akhkhir", R.drawable.a72,"The Procrastinator"),
 new NamesModel("The Last, The One whose Existence is without an end.", "Al-Awwal", R.drawable.a73, "The Very First"),
 new NamesModel( "The Manifest, The One that nothing is above Him and nothing is underneath Him, hence He exists without a place. He, The Exalted, His Existence is obvious by proofs and He is clear from the delusions of attributes of bodies.","Al-Akhir", R.drawable.a74, "The Infinite Last One"),
 new NamesModel("The Hidden, The One that nothing is above Him and nothing is underneath Him, hence He exists without a place. He, The Exalted, His Existence is obvious by proofs and He is clear from the delusions of attributes of bodies.", "Az-Zahir",  R.drawable.a75, "The Perceptible"),
 new NamesModel("The Governor, The One who owns things and manages them.","Al-Batin",  R.drawable.a76," The Imperceptible"),
 new NamesModel("The Most Exalted, The High Exalted, The One who is clear from the attributes of the creation.",  "Al-Wali", R.drawable.a77,"The Holder of Supreme Authority"),
 new NamesModel("The Source of All Goodness, The Righteous, The One who is kind to His creatures, who covered them with His sustenance and specified whoever He willed among them by His support, protection, and special mercy.", "Al-Muta ali", R.drawable.a78,"The Extremely Exalted One"),
 new NamesModel("The Relenting, The One who grants repentance to whoever He willed among His creatures and accepts his repentance.",
                "Al-Barr", R.drawable.a79,"The Fountain-Head of Truth"),



        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.displayNames);
        NamesAdapterClass adapter = new NamesAdapterClass(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}