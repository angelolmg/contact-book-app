import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-confirm-deletion-dialog',
  templateUrl: './confirm-deletion-dialog.component.html',
  styleUrls: ['./confirm-deletion-dialog.component.scss']
})
export class ConfirmDeletionDialogComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public data: string, public dialog: MatDialog,
              public dialogRef: MatDialogRef<ConfirmDeletionDialogComponent>) { }

  confirmDelete() {
    this.dialogRef.close(1);
  }
}
