package api.tickets;

import java.util.Date;

public class TicketResponsePojo {
    private int id;
    private String due_date;
    private String assigned_to;
    private String title;
    private Date created;
    private Date modified;
    private String submitter_email;
    private int status;
    private boolean on_hold;
    private String description;
    private String resolution;
    private int priority;
    private Date last_escalation;
    private String secret_key;
    private int queue;
    private int kbitem;
    private int merged_to;

    public TicketResponsePojo(int id, String due_date, String assigned_to, String title, Date created, Date modified, String submitter_email, int status, boolean on_hold, String description, String resolution, int priority, Date last_escalation, String secret_key, int queue, int kbitem, int merged_to) {
        this.id = id;
        this.due_date = due_date;
        this.assigned_to = assigned_to;
        this.title = title;
        this.created = created;
        this.modified = modified;
        this.submitter_email = submitter_email;
        this.status = status;
        this.on_hold = on_hold;
        this.description = description;
        this.resolution = resolution;
        this.priority = priority;
        this.last_escalation = last_escalation;
        this.secret_key = secret_key;
        this.queue = queue;
        this.kbitem = kbitem;
        this.merged_to = merged_to;
    }

    public int getId() {
        return id;
    }

    public String getDue_date() {
        return due_date;
    }

    public String getAssigned_to() {
        return assigned_to;
    }

    public String getTitle() {
        return title;
    }

    public Date getCreated() {
        return created;
    }

    public Date getModified() {
        return modified;
    }

    public String getSubmitter_email() {
        return submitter_email;
    }

    public int getStatus() {
        return status;
    }

    public boolean isOn_hold() {
        return on_hold;
    }

    public String getDescription() {
        return description;
    }

    public String getResolution() {
        return resolution;
    }

    public int getPriority() {
        return priority;
    }

    public Date getLast_escalation() {
        return last_escalation;
    }

    public String getSecret_key() {
        return secret_key;
    }

    public int getQueue() {
        return queue;
    }

    public int getKbitem() {
        return kbitem;
    }

    public int getMerged_to() {
        return merged_to;
    }
}
