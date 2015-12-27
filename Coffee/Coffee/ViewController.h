//
//  ViewController.h
//  Coffee
//
//  Created by rektified on 12/26/15.
//  Copyright © 2015 rektified. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ViewController : UIViewController

@property (weak, nonatomic) IBOutlet UITextField *waterText;
@property (weak, nonatomic) IBOutlet UITextField *ratioText;
@property (weak, nonatomic) IBOutlet UITextField *coffeeText;

- (IBAction)calculate:(id)sender;


@end

