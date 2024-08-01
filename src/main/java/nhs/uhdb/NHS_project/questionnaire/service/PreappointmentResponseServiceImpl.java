package nhs.uhdb.NHS_project.questionnaire.service;

import nhs.uhdb.NHS_project.diary.model.DiaryEntryRepository;
import nhs.uhdb.NHS_project.questionnaire.model.CellulitisIncident;
import nhs.uhdb.NHS_project.questionnaire.model.CellulitisIncidentRepository;
import nhs.uhdb.NHS_project.questionnaire.model.PreappointmentResponse;
import nhs.uhdb.NHS_project.questionnaire.model.PreappointmentResponseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PreappointmentResponseServiceImpl implements PreappointmentResponseService {

    private PreappointmentResponseRepository preappointmentResponseRepository;
    private CellulitisIncidentRepository cellulitisIncidentRepository;

    //Using logger for debugging
    private static final Logger LOGGER = Logger.getLogger(PreappointmentResponseServiceImpl.class.getName());

    //Constructor to initialise repositories
    public PreappointmentResponseServiceImpl(PreappointmentResponseRepository preappointmentResponseRepository, CellulitisIncidentRepository cellulitisIncidentRepository) {
        this.preappointmentResponseRepository = preappointmentResponseRepository;
        this.cellulitisIncidentRepository = cellulitisIncidentRepository;
    }

    //Method to retrieve a PreappointmentResponse by user ID and date
    @Override
    public PreappointmentResponse getResponseByUserIdAndDate(Long user_id, LocalDate date) {
        return preappointmentResponseRepository.getResponseByUserIdAndDate(user_id, date);
    }

    //Method to save a new PreappointmentResponse and associated CellulitisIncidents to the database
    @Override
    public Long saveResponse(PreappointmentResponse preappointmentResponse) {
        Long responseId = preappointmentResponseRepository.saveResponse(preappointmentResponse);
        for (CellulitisIncident incident : preappointmentResponse.getEpisodes()) {
            Long incidentId = cellulitisIncidentRepository.saveIncident(incident, responseId);

            //Log the successful save of the CellulitisIncident
            LOGGER.info("Saved CellulitisIncident with ID: " + incidentId);
        }
        return responseId;
    }

    //Method to retrieve all PreappointmentResponses by user ID
    @Override
    public List<PreappointmentResponse> getResponsesByUserId(Long user_id) {
        return preappointmentResponseRepository.getResponsesByUserId(user_id);
    }


}