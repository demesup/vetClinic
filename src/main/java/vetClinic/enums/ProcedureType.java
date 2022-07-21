package vetClinic.enums;

public enum ProcedureType {
    LUXATING_PATELLA_SURGERY("""
            Medial patellar luxation (MPL) surgery is performed to correct the luxation, or "popping out" \s
            of the kneecap (called the patella).  A luxating patella is caused by a congenital malformation \s
            which creates abnormal forces on the kneecap, causing it to slide out of the groove (called the \s
            patellar groove) in which it normally sits.\s"""),

    COLONOSCOPY("""
            A colonoscopy is a diagnostic test which inserts a flexible tube with a camera into the patient’s\s
            rectum.  This procedure aids to visualize the inner lining of the rectum and colon. During this \s
            procedure the veterinarian will also be able to take biopsy samples of any suspicious lesions. The \s
            biopsies are then sent to a referral laboratory for histopathology. The results of the biopsy will \s
            determine if the polyps are benign or malignant. \s"""),

    ENDOSCOPY("""
            Endoscopy is the use of video instruments to investigate and possibly biopsy certain body cavities.\s
            It is a noninvasive procedure which means no surgical incisions are required. For the patient, this\s
            means a short anesthetic period with a rapid recovery.\s"""),

    EYELID_TUMOR_REMOVAL_SMALL("""
            Eyelid tumors in dogs are very common, especially in older dogs. The majority of these \s
            eyelid tumors are non-cancerous and many times they occur on the glands that line the eyelid margins.\s"""),

    EYELID_TUMOR_REMOVAL_WITH_RECONSTRUCTION("""
            Eyelid tumors in dogs are very common, especially in older dogs. The majority of these eyelid\s
            tumors are non-cancerous and many times they occur on the glands that line the eyelid margins.\s
            Sometimes the tumor is large and following mass removal reconstruction of the eyelid is necessary.\s"""),

    SWIM_THERAPY_PACKAGE("""
            Long used for humans suffering from arthritis pain and disabilities, hydrotherapy has \s
            only become available for dogs in recent years. Canine water therapy is a rehabilitation \s
            program that allows dogs to exercise in water. Swim therapy is a great, low impact \s
            resistance exercise designed to help strengthen muscles. The fluid environment gives \s
            them greater support to minimize stress on their joints, while at the same time creating \s
            enough resistance so that they can exercise their muscles.\s"""),

    THERAPY_LASER("""
            Laser technology is used every day to treat animals that are in pain or facilitate\s
            healing of an animal’s wound or injury. Simply put, patients  who suffer from any combination of\s
            pain, inflammation, or slow-healing wounds can benefit from laser treatments. For pets who have\s
            had surgery or a traumatic injury, the laser is used to speed healing.\s"""),

    TECA_BO("""
            Total Ear Canal Ablation & Bulla Osteotomy.
            Sometimes an ear infection is simply hopeless. Perhaps the organism growing is too resistant \s
            for treatment,the ear canal is so scarred and narrowed that external cleaning is a useless \s
            activity or the ear canal has actually mineralized from chronic irritation. The bottom line \s
            is that this degree of irreversible disease requires surgical treatment. In such cases, all \s
            the diseased tissue: the entire ear canal, bones of the middle ear etc. are simply removed, \s
            the middle ear is drained, and the healthy tissue around the ear is closed. The operation \s
            involves the removal of the entire ear canal. The outside part of the ear (the pinna) and \s
            the hearing organ (inner ear) itself are left in position. Following removal of the diseased \s
            ear canal, part of the bony wall of the tympanic bulla (middle ear) is also removed to \s
            facilitate removal of infected material from the middle ear chamber.\s""");
    final String description;

    ProcedureType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.name() +
                "\n" + description ;
    }
}
