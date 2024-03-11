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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.WorkOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.feature.resume.ui.models.TabContentType
import com.feature.resume.ui.models.TabItem

@Composable
fun ResumeScreen(
    uiState: UiState<ResumeWithTags?>,
    onRetry: () -> Unit,
    modifier: Modifier,
    screenMode: MutableState<ResumeUiMode>
) {
    val tabItems = listOf(
        TabItem(
            contentType = TabContentType.COMMON,
            unselectedIcon = Icons.Outlined.AccountBox,
            selectedIcon = Icons.Filled.AccountBox
        ),
        TabItem(
            contentType = TabContentType.EDUCATION,
            unselectedIcon = Icons.Outlined.School,
            selectedIcon = Icons.Filled.School
        ),
        TabItem(
            contentType = TabContentType.JOB_EXPERIENCE,
            unselectedIcon = Icons.Outlined.WorkOutline,
            selectedIcon = Icons.Filled.Work
        )


    )
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    Column(
        modifier = modifier.fillMaxSize()
    ) {

        TabRow(selectedTabIndex = selectedTabIndex) {
            tabItems.forEachIndexed { index, tabItem ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(text = tabItem.contentType.toString())
                    },
                    icon = {
                        Icon(
                            imageVector = if (index == selectedTabIndex) {
                                tabItem.selectedIcon
                            } else {
                                tabItem.unselectedIcon
                            },
                            contentDescription = tabItem.contentType.toString()
                        )
                    }
                )
            }

        }
        when (uiState) {
            is UiState.Success -> ResumeDetails(
                uiState.data,
                tabItems[selectedTabIndex].contentType,
                screenMode
            )

            is UiState.Loading -> LoadingScreen()
            is UiState.Error -> ErrorScreen(uiState.message, retryAction = onRetry)
        }
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
    tabContent: TabContentType,
    screenMode: MutableState<ResumeUiMode>
) {
    if (data != null) {
        when (tabContent) {
            TabContentType.COMMON -> {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    CandidateInfoBox(data.resume.candidateInfo, screenMode)
                    EditableRow(
                        stringResource(id = R.string.free_form),
                        data.resume.freeForm,
                        screenMode
                    )
                    LazyRow {
                        items(data.tagList) {
                            Card {
                                Text(text = it[1])
                            }
                        }
                    }
                }

            }

            TabContentType.EDUCATION -> {
                LazyColumn {
                    itemsIndexed(data.resume.educationList) { index, education ->
                        EducationCard(education, screenMode) {
                            TODO("Deletion logic - not yet implemented")
                        }
                    }
                }
            }

            TabContentType.JOB_EXPERIENCE -> {
                LazyColumn {
                    items(data.resume.jobExperienceList) { jobExperience ->
                        JobExperienceCard(jobExperience, screenMode) {
                            TODO("Deletion logic - not yet implemented")
                        }
                    }
                }
            }
        }

    }
}


@Composable
fun CandidateInfoBox(candidateInfo: CandidateInfo, screenMode: MutableState<ResumeUiMode>) {
    Column {
        EditableRow(stringResource(id = R.string.candidate_name), candidateInfo.name, screenMode)
        EditableRow(
            stringResource(id = R.string.birthdate),
            candidateInfo.birthDate.toString(),
            screenMode
        )
        DropdownMenuWithEnumContent(
            label = stringResource(id = R.string.gender),
            screenMode = screenMode,
            candidateInfo.gender
        )
        DropdownMenuWithEnumContent(
            label = stringResource(id = R.string.profession),
            screenMode = screenMode,
            candidateInfo.profession
        )
        DropdownMenuWithEnumContent(
            label = stringResource(id = R.string.relocation),
            screenMode = screenMode,
            candidateInfo.relocation
        )
        EditableRow(stringResource(id = R.string.email), candidateInfo.contact.email, screenMode)
        EditableRow(stringResource(id = R.string.phone), candidateInfo.contact.phone, screenMode)
    }
}

@Composable
fun EditableRow(label: String, editableString: String, screenMode: MutableState<ResumeUiMode>) {
    val string = remember { mutableStateOf(editableString) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label)
        Spacer(modifier = Modifier.width(10.dp))
        TextField(
            value = string.value,
            onValueChange = { string.value = it },
            enabled = screenMode.value == ResumeUiMode.EDIT,
            singleLine = true
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
inline fun <reified E : Enum<E>> DropdownMenuWithEnumContent(
    label: String,
    screenMode: MutableState<ResumeUiMode>,
    enum: E
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label)
        Spacer(modifier = Modifier.width(10.dp))
        var expanded by remember { mutableStateOf(false) }
        var value by remember { mutableStateOf(enum) }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                if (screenMode.value == ResumeUiMode.EDIT) {
                    expanded = !expanded
                }
            }
        ) {
            TextField(
                value = value.toString(),
                onValueChange = {},
                enabled = screenMode.value == ResumeUiMode.EDIT,
                trailingIcon = {
                    if (screenMode.value == ResumeUiMode.EDIT) ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                modifier = Modifier.menuAnchor(),
                singleLine = true,
                readOnly = true
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                enumValues<E>().forEach {
                    DropdownMenuItem(
                        text = { Text(text = it.toString()) },
                        onClick = {
                            value = it
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun EducationCard(
    education: Education, screenMode: MutableState<ResumeUiMode>, onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = stringResource(id = R.string.education, education.type))
                Text(
                    text = stringResource(
                        id = R.string.education_period,
                        education.startYear,
                        education.endYear
                    )
                )
                Text(
                    text = stringResource(
                        id = R.string.education_description,
                        education.description
                    )
                )
            }
            if (screenMode.value == ResumeUiMode.EDIT) {
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "test"
                    )
                }
            }
        }

    }

}

@Composable
fun JobExperienceCard(
    jobExperience: JobExperience, screenMode: MutableState<ResumeUiMode>, onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = stringResource(id = R.string.company_name, jobExperience.companyName))
                Text(
                    text = stringResource(
                        id = R.string.job_period,
                        jobExperience.dateStart,
                        jobExperience.dateEnd
                    )
                )
                Text(
                    text = stringResource(
                        id = R.string.job_description,
                        jobExperience.description
                    )
                )
            }
            if (screenMode.value == ResumeUiMode.EDIT) {
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "test"
                    )
                }
            }
        }
    }
}

