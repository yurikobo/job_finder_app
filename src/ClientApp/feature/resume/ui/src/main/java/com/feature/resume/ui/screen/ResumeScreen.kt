package com.feature.resume.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.core.common.UiState
import com.core.common.models.CandidateInfo
import com.core.common.models.Education
import com.core.common.models.JobExperience
import com.core.common.models.ResumeWithTags
import com.feature.resume.ui.R
import com.feature.resume.ui.models.ResumeUiMode

@Composable
fun ResumeScreen(
    uiState: UiState<ResumeWithTags?>,
    onRetry: () -> Unit,
    modifier: Modifier,
    screenMode: MutableState<ResumeUiMode>
) {
    when (uiState) {
        is UiState.Success -> ResumeDetails(uiState.data, screenMode, modifier)
        is UiState.Loading -> LoadingScreen()
        is UiState.Error -> ErrorScreen(uiState.message, retryAction = { onRetry.invoke() })
    }
}

@Composable
fun ErrorScreen(message: String?, retryAction: () -> Unit) {
    Column {
        message?.let { Text(it) }
        Button(onClick = retryAction) { Text(stringResource(R.string.retry)) }
    }
}

@Composable
fun LoadingScreen() {
    Text(stringResource(R.string.now_loading))
}

@Composable
fun ResumeDetails(
    data: ResumeWithTags?,
    screenMode: MutableState<ResumeUiMode>,
    modifier: Modifier
) {
    if (data != null) {
        val isEditable = screenMode.value == ResumeUiMode.EDIT
        Column(modifier = modifier) {
            Text(text = "Candidate info")
            CandidateInfoBox(data.resume.candidateInfo, isEditable)
            Text(text = "Education List")
            LazyColumn(modifier = Modifier.weight(1F)) {
                items(data.resume.educationList) { education ->
                    EducationCard(education, isEditable)
                }
            }
            Text(text = "Job experience list")
            LazyColumn(modifier = Modifier.weight(1F)) {
                items(data.resume.jobExperienceList) { jobExperience ->
                    JobExperienceCard(jobExperience, isEditable)
                }
            }
            Text(text = "Free form")

            EditableRow(stringResource(id = R.string.free_form), data.resume.freeForm, isEditable)
        }
    }
}


@Composable
fun CandidateInfoBox(candidateInfo: CandidateInfo, isEditable: Boolean) {
    Column {
        EditableRow(stringResource(id = R.string.candidate_name), candidateInfo.name, isEditable)
        DropdownMenuWithEnumContent(
            label = stringResource(id = R.string.gender),
            isEditable = isEditable,
            candidateInfo.gender
        )
        DropdownMenuWithEnumContent(
            label = stringResource(id = R.string.profession),
            isEditable = isEditable,
            candidateInfo.profession
        )
        EditableRow(stringResource(id = R.string.email), candidateInfo.contact.email, isEditable)
        EditableRow(stringResource(id = R.string.phone), candidateInfo.contact.phone, isEditable)
    }
}

@Composable
fun EditableRow(label: String, editableString: String, isEnabled: Boolean) {
    val string = remember { mutableStateOf(editableString) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label)
        Spacer(modifier = Modifier.width(10.dp))
        TextField(
            value = string.value,
            onValueChange = { string.value = it },
            readOnly = !isEnabled
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
inline fun <reified E : Enum<E>> DropdownMenuWithEnumContent(
    label: String,
    isEditable: Boolean,
    enum: E
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label)
        Spacer(modifier = Modifier.width(10.dp))
        var expanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = if (isEditable) expanded else false,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = enum.toString(),
                onValueChange = {},
                enabled = isEditable,
                trailingIcon = {
                    if (isEditable) {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    }
                },
                modifier = Modifier.menuAnchor()
//                    .padding(start = 20.dp, top = 10.dp, end = 10.dp)
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                enumValues<E>().forEach {
                    DropdownMenuItem(
                        text = { Text(text = it.toString()) },
                        onClick = { expanded = false }
                    )
                }
            }
        }
    }
}


@Composable
fun EducationCard(education: Education, isEditable: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(text = stringResource(id = R.string.education, education.type))
        Text(
            text = stringResource(
                id = R.string.education_period,
                education.startYear,
                education.endYear
            )
        )
        Text(text = stringResource(id = R.string.education_description, education.description))
    }

}

@Composable
fun JobExperienceCard(jobExperience: JobExperience, isEditable: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(text = stringResource(id = R.string.company_name, jobExperience.companyName))
        Text(
            text = stringResource(
                id = R.string.job_period,
                jobExperience.dateStart,
                jobExperience.dateEnd
            )
        )
        Text(text = stringResource(id = R.string.job_description, jobExperience.description))
    }
}

