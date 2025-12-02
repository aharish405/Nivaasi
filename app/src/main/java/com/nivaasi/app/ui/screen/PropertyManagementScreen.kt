package com.nivaasi.app.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nivaasi.app.data.model.Bed
import com.nivaasi.app.data.model.BedStatus
import com.nivaasi.app.data.model.Room
import com.nivaasi.app.ui.theme.*
import com.nivaasi.app.ui.viewmodel.PropertyViewModel

@Composable
fun PropertyManagementScreen(
    viewModel: PropertyViewModel = viewModel()
) {
    val property by viewModel.property.collectAsState()
    val selectedFloorIndex by viewModel.selectedFloorIndex.collectAsState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
    ) {
        // Floor Tabs
        property?.let { prop ->
            ScrollableTabRow(
                selectedTabIndex = selectedFloorIndex,
                containerColor = Color.White,
                contentColor = SkyBlue,
                edgePadding = 16.dp
            ) {
                prop.floors.forEachIndexed { index, floor ->
                    Tab(
                        selected = selectedFloorIndex == index,
                        onClick = { viewModel.selectFloor(index) },
                        text = {
                            Text(
                                text = floor.name,
                                fontWeight = if (selectedFloorIndex == index) 
                                    FontWeight.Bold 
                                else 
                                    FontWeight.Normal
                            )
                        }
                    )
                }
            }
            
            // Rooms Grid
            val selectedFloor = prop.floors.getOrNull(selectedFloorIndex)
            selectedFloor?.let { floor ->
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(floor.rooms) { room ->
                        RoomCard(room = room)
                    }
                }
            }
        }
    }
}

@Composable
fun RoomCard(room: Room) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Room Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Room ${room.roomNumber}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    StatusChip(
                        label = "Capacity: ${room.capacity}",
                        color = Color.Gray
                    )
                    StatusChip(
                        label = "Available: ${room.availableCount}",
                        color = BedAvailable
                    )
                    StatusChip(
                        label = "Occupied: ${room.occupiedCount}",
                        color = BedOccupied
                    )
                }
            }
            
            Divider()
            
            // Bed Grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                room.beds.forEach { bed ->
                    BedIcon(
                        bed = bed,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun BedIcon(
    bed: Bed,
    modifier: Modifier = Modifier
) {
    val bedColor = when (bed.status) {
        BedStatus.AVAILABLE -> BedAvailable
        BedStatus.OCCUPIED -> BedOccupied
        BedStatus.NOTICE -> BedNotice
        BedStatus.BLOCKED -> BedBlocked
        BedStatus.VACATED -> BedVacated
    }
    
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(bedColor.copy(alpha = 0.2f), shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Bed,
                contentDescription = "Bed ${bed.bedNumber}",
                tint = bedColor,
                modifier = Modifier.size(32.dp)
            )
        }
        Text(
            text = bed.bedNumber,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = bed.status.name,
            style = MaterialTheme.typography.labelSmall,
            color = bedColor,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun StatusChip(label: String, color: Color) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = color.copy(alpha = 0.1f)
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelSmall,
            color = color,
            fontWeight = FontWeight.Medium
        )
    }
}
