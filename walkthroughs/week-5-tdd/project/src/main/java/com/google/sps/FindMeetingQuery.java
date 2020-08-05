// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.stream.Collectors;

import static java.util.Collections.disjoint;


public final class FindMeetingQuery {

  /**
   * 
   * */
  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {

    long requestedDuration = request.getDuration();
    //Select all the events that contain mandatory attendee, and add them sorted by start to a sorted set
    Collection<String> requestedAttendees = request.getAttendees();
    SortedSet<TimeRange> takenTimeRangesSorted = events.stream()
            .filter((event)->hasAttendee(event, requestedAttendees))
            .map(Event::getWhen)
            .collect(Collectors.toCollection(()->new TreeSet<>(TimeRange.ORDER_BY_START)));

    TimeRange guardian = TimeRange.fromStartDuration(TimeRange.END_OF_DAY+1, 0); //Shouldn't timerange set outside the minutes of the day be invalid?
    takenTimeRangesSorted.add(guardian); //Could be done by an additional if at the end as well

    int lastFinish = TimeRange.START_OF_DAY;
    Collection<TimeRange> appropriateTimeranges = new ArrayList<TimeRange>();
    for(TimeRange nextEvent: takenTimeRangesSorted){
      int nextEventStart = nextEvent.start();
      int nextEventEnd = nextEvent.end();
      if(lastFinish < nextEventStart){ //should I extract nextEvent.start() to a local variable?
        TimeRange empty = TimeRange.fromStartEnd(lastFinish, nextEventStart, false);
        if(empty.duration() >= requestedDuration){
          appropriateTimeranges.add(empty);
        }
      }
      if(lastFinish < nextEventEnd){
        lastFinish = nextEventEnd;
      }
    }

    return appropriateTimeranges;
  }

  /**
   * Returns whether any of the attendees in the passed collection is participating in the event.
   * */
  private boolean hasAttendee(Event event, Collection<String> attendees){//will it delete the attendees if the event?
    return !disjoint(event.getAttendees(), attendees);
  }
}
