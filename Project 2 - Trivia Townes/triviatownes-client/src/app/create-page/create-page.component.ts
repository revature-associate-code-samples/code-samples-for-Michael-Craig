import { Component, OnInit } from '@angular/core';
import { GlobalsService } from '../globals.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-create-page',
  templateUrl: './create-page.component.html',
  styleUrls: ['./create-page.component.scss']
})

export class CreatePageComponent implements OnInit {
  category: string;
  seats: string;
  questions: string;
  difficulty: string;
  private = false;
  username: string;
  name: string;
  key: string;

  constructor(
    public router: Router,
    public globals: GlobalsService
  ) { }

  ngOnInit() {
    this.newUser();
  }

  // called when user hits landing page
  newUser() {
    $.ajax({
      url: this.globals.getApiUrl() + 'new-user',
      method: 'GET',
      crossDomain: true,
      xhrFields: { withCredentials: true },
      success: function (result) {
        console.log('Created Session');
      },
      error: function (result) {
        console.log('Something went wrong');
      }
    });
  }

  selectedCategory(cat) {
    this.category = cat;
  }

  selectedSeats(num: number) {
    this.seats = num + '';
  }

  selectedQuestions(num: number) {
    this.questions = num + '';
  }

  selectedDifficulty(str: string) {
    this.difficulty = str + '';
  }

  privateOrPublic() {
    this.private = !this.private;
  }

  lobbyName(str: string) {
    this.name = str;
  }

  setUsername(str: string) {
    this.username = str;
  }

  // communicates with backend to create lobby
  create() {
    const x = this;

    if (!(x.category && x.seats && x.questions && x.name && x.username)) {
      alert('Missing values for game creation');
      return;
    }

    let scope: string;
    if (x.private === true) {
      scope = 'true';
    } else {
      scope = 'false';
    }

    x.globals.setScope(scope);

    $.ajax({
      url: x.globals.getApiUrl() + 'create-game',
      method: 'POST',
      crossDomain: true,
      xhrFields: { withCredentials: true },
      data: {
        category: x.category,
        seats: x.seats,
        questions: x.questions,
        username: x.username,
        name: x.name,
        private: scope
      },
      success: function (res) {
        x.globals.setLobbyKey(res['lobbyId']);
        x.globals.setUsername(x.username);
        x.globals.setUserId(res['userId']);
        x.globals.setGameCategory(res['category']);
        x.globals.setLobbyQuestions(res['questions']);
        x.globals.setLobbyName(res['lobbyName']);
        x.globals.setIsLeader(true);
        x.router.navigate(['waiting']);
      },
      error: function (res) {
        alert('There was a problem connecting to lobby...');
      }
    });
  }

}
