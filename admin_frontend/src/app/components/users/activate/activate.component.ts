import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { User } from '../../../core/model/user';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { environment } from 'src/environments/environment';
import { AuthService } from 'src/app/core/services/auth.service';
import { StorageService } from 'src/app/core/services/storage.service';

@Component({
  selector: 'app-activate',
  templateUrl: './activate.component.html',
  styleUrls: ['./activate.component.scss']
})
export class ActivateComponent implements OnInit {

  activateForm: FormGroup;
  user: User;
  loading = false;
  expired = true;
  activated = false;

  constructor(
    private authService: AuthService,
    private messageService: MessageService,
    private formBuilder: FormBuilder,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private storageService: StorageService
  ) { }

  ngOnInit(): void {

    this.activateForm = this.formBuilder.group({
      password: ['', [Validators.required, Validators.pattern(
        new RegExp('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$'))]],
      repeat: ['', [this.passwordConfirmed()]]
    });

    this.activateForm.controls.password.valueChanges
    // tslint:disable-next-line: deprecation
    .subscribe(() => this.activateForm.controls.repeat.updateValueAndValidity());

    // tslint:disable-next-line: deprecation
    this.activatedRoute.queryParams.subscribe((params: Params) => {
      if (!params.q) {
        this.storageService.removeToken();
        this.router.navigate([environment.loginRoute]);
        return;
      }

      // tslint:disable-next-line: deprecation
      this.authService.getDisabled(params.q).subscribe((user: User) => {
        if (user){
          this.user = user;
          this.expired = new Date(this.user.activationExpiration).getTime() <= new Date().getTime();
        }
        else{
          this.storageService.removeToken();
          this.router.navigate([environment.loginRoute]);
        }
      });
    });
  }

  activate(): void {
    if (this.activateForm.invalid) {
      return;
    }

    this.loading = true;
    // tslint:disable-next-line: deprecation
    this.authService.activate({...this.activateForm.value, ...{uuid: this.user.activationLink}}).subscribe((response: User) => {
      this.loading = false;
      if (response){
        this.activated = true;
        this.messageService.add({
          severity: 'success',
          summary: 'Success',
          detail: 'You can now log into our services.'
        });
      }
      else{
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: `Error occured while activating!`
        });
      }
    });
  }

  private passwordConfirmed(): ValidatorFn{
    return (control: AbstractControl): ValidationErrors => {
      const passwordConfirmed = control.parent ?
      control.value === control.parent.get('password').value : true;
      return passwordConfirmed ? null : {passwordError: true};
    };
  }

}
