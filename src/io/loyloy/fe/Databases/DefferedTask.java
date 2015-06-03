package io.loyloy.fe.Databases;

import io.loyloy.fe.API.Account;

public abstract class DefferedTask implements Runnable
{
    private final Account account;

    public DefferedTask( Account account )
    {
        this.account = account;
    }

    public Account getAccount()
    {
        return account;
    }
}
