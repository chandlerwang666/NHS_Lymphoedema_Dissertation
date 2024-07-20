package nhs.uhdb.NHS_project.diary.services;

import nhs.uhdb.NHS_project.diary.model.DiaryEntry;

import java.time.LocalDate;
import java.util.List;

public interface DiaryEntryService {
    Long createDiaryEntry(DiaryEntry diaryEntry);
    DiaryEntry getDiaryEntryByUserIdAndDate(Long user_id, LocalDate date);
    List<DiaryEntry> getDiaryEntriesByUserId(Long user_id);
}
