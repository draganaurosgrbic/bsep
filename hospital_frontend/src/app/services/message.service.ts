import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { EMPTY_PAGE, PAGE_SIZE } from 'src/app/utils/constants';
import { Message } from 'src/app/models/message';
import { MessageSearch } from 'src/app/models/message-search';
import { Page } from 'src/app/models/page';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(private http: HttpClient) { }

  private readonly API_PATH = 'api/messages';

  findAll(page: number, search: MessageSearch): Observable<Page<Message>>{
    const params = new HttpParams().set('page', page + '').set('size', PAGE_SIZE + '');
    return this.http.post<Page<Message>>(this.API_PATH, search, {params}).pipe(
      catchError(() => of(EMPTY_PAGE))
    );
  }

}
